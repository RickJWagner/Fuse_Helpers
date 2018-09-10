This example demonstrates a way to handle Exceptions with Camel, even when using advanced constructs like 'Load Balancer'.

In this example, there are 2 web services that are load balanced.  Both of them use a utilty ('FateDecider') that might result in throwing a BusinessException, SystemException or plain Exception.

If a plain Exception is thrown, it should later be evaluated and cast to a SystemException or BusinessException for the calling client to view.

If a BusinessException is thrown, there is no hope of recovery.  The user should see the BusinessException in the stack trace.

If there is a SystemException, there is hope that Camel might save things through re-tries.  Sometimes we will get back a good response, sometimes we will get back a SystemException (depending on whethere the system could 'recover' or not).

-------------------------------------------------------------

To use the application, invoke via browser:

http://localhost:8080/camel-test-spring?name=SomeValidName     // 'Good, normal' response
http://localhost:8080/camel-test-spring?name=E                 // plain Exception processing
http://localhost:8080/camel-test-spring?name=BE                // BusinessException processing
http://localhost:8080/camel-test-spring?name=SE                // SystemException processing