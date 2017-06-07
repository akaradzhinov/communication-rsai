# communication-rsai

#Building of the project:
    use command: mvn clean install

#Running the project:
    use command: mvn spring-boot:run

#Application properties:
    communication.retry.attempts - number of times the request is going to be retried in case of failure
    server.port - the port on which the application is going to be started on

#ModuleMappings
    This enum should contain the mappings to the external urls to the other modules.
