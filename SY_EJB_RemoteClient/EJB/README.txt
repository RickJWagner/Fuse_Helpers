
This directory contains 2 SwitchYard applications.

One offers a WSDL interface to kick things off.  The service is accessed at http://localhost:8080/MyService/MyService

The first (producer) service is a SwitchYard application that uses a Camel route.  The Camel route calls a bean that is a Remote EJB client.

The second (ejb) application is a SwitchYard application where one of the services is a Stateless EJB.  To show SwitchYard aspects, the EJB calls a helper service.
