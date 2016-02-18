This project demonstrates a few things:

- A web service proxy built with Camel-on-EAP.
- Exception handling for such a proxy.
- An unreliable web service that sometimes returns a fault.

The unreliable web service (which is to be proxied) is unremarkable.  It uses a random number to decide when to experience a fault.

The proxy uses wsdl that was generated from the unreliable webservice.  (Via wsconsume in the JBoss bin directory.)  The generated artifacts are included in the source for the Proxy project.
The proxy project is built on the camel-jaxws quickstart.  (There are some leftover remnants in the project.)  The important parts:
- GreetingServiceImpl, which offers the web contract to the client.  It uses a Camel Proxy to invoke the route discussed next.
- JaxwsRouteBuilder, which is a camel route that proxies the backend service (unreliable webservice).
-- Note the use of the Predicate.  More of these can be built, allowing customization of filtering and routing.
-- The request to the backend service is constructed in this class.  This is useful in cases where the wsdl for the proxied service does not match the wsdl for the proxy.
-- A second route is used to process the faults.  More than one could be used if this is desirable for different use cases.


