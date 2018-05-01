Introduction
============

This sample shows a SwitchYard application that exposes an HTTP gateway to a Camel/XML route.

The Camel route randomly throws Exceptions, which are caught in a try/catch block.  We see an ineffective way to set a custom response (in the Camel catch block) as well as a more effective method using a custom SwitchYard composer.

It also shows Context Mapping for the output, including use of 'excludes' to avoid leaking extra information.

Access the built application at http://localhost:8080/mypath
