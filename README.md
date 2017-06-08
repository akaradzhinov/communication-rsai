# communication-rsai

#Building of the project:

    use command:    mvn clean install

#Running the project:

    use command:    mvn spring-boot:run

#Application properties:

    * communication.retry.attempts - number of times the request is going to be retried in case of failure
    * server.port - the port on which the application is going to be started on

#ModuleMappings

    This enum should contain the mappings to the external urls to the other modules.
    GEAR("gear","www.example.come") -> this represents the module from which the data came (in this case module "gear")
                                       and the url of the endpoint where the data should be sent (www.example.come)
    
#URL for sending data

    {baseUrl}:{server.port}/communication/send
    
#Example request and payload

    POST ->     http://localhost:8080/communication/send
    HEADERS ->  Content-type: application/json
    body -> 
    {
      "moduleName":"display",
      "value": {
                 "gear":4,
                 "rpm":4500
               }
    }

#GET Requests

    Two endpoints for executing GET requests:

        * {baseUrl}:{server.port}/communication/message/{moduleName} - returns the last value saved for this moduleName

            Example request:
                http://localhost:8080/communication/message/display

            Example response:
                {"gear":4,"rpm":4500}


        * {baseUrl}:{server.port}/communication/messages - returns last values for all modules

            Example request:
                http://localhost:8080/communication/messages

            Example response:
                [
                   {
                      "moduleName":"belt",
                      "value":{
                         "warnForSeatbelt":true,
                         "warningSeverity":"high"
                      }
                   },
                   {
                      "moduleName":"speed",
                      "value":180
                   },
                   {
                      "moduleName":"gear",
                      "value":{
                         "gear":4,
                         "rpm":4500
                      }
                   }
                ]