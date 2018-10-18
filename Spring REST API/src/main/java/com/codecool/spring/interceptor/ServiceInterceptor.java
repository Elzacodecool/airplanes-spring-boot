package com.codecool.spring.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

        if (response.getStatus() >= 400) {
            LOGGER.info("[afterCompletion] Can't " + method.getName()
                    + ", object not found [error status: " + response.getStatus() + "]");
        } else {
            LOGGER.info("[afterCompletion] " + method.getName() + " is completed");
        }
    }
}
