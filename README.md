# airplanes-spring-boot

## REST API using Boot.

### Requirements
  - Use Spring Boot to create the project.
  - Define from 2 to 3 resources.
  - Handle CRUD operations on these resources.
  - Between two of available resources, relationship has to be defined.
  - You HAVE to use H2 or PostgreSQL database.
  - Keep separate concerns of your app - controller, service, repository. Make sure responsibilities are not mixed, e.g. controller is responsible for operating on HTTP layer and passing data further, only.
  
### So the new requirements are:

  - Logging of every CRUD operation and exception to a text file using log4j
  - Data should be safe deleted - archived instead of physically deleted. Users should not see archived data but the data should be present in the database
  - send an email whenever an status code 500 is to be sent
