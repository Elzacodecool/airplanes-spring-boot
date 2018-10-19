package com.codecool.spring.interceptor;

import com.codecool.spring.model.Mail;
import com.codecool.spring.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ServiceInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LogManager.getLogger(ServiceInterceptor.class.getName());

    private final EmailService emailService;

    @Autowired
    public ServiceInterceptor(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

         HandlerMethod handlerMethod = (HandlerMethod) handler;
         Method method = handlerMethod.getMethod();
         LOGGER.info("[preHandle] start method: {} {} - from path: {}",
            request.getMethod(), method.getName(), request.getRequestURI());

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        LOGGER.info("[postHandle] handle the request...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
        if (exception != null){
            exception.printStackTrace();
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (response.getStatus() != 500) LOGGER.info("[afterCompletion] " + method.getName() + " is completed");
        else {
            LOGGER.info("[afterCompletion] Can't " + method.getName()
                    + ", object not found [error status: " + response.getStatus() + "]");
            sendEmail(response, method);
        }
    }

    private void sendEmail(HttpServletResponse response, Method method) {
        Mail mail = new Mail();
        mail.setFrom("noreplyspring@wp.pl");
        mail.setTo("noreplyspring@wp.pl");
        mail.setSubject("Server Error");
        mail.setContent("Error 500 occurred.\n" + "[afterCompletion] Can't " + method.getName()
                + ", object not found [error status: " + response.getStatus() + "]");
        emailService.sendSimpleMessage(mail);
    }
}
