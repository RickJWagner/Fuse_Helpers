
Problem:  Write a SwitchYard application that will use REST as a binding and can convert input XML to JSON.

The provided solution comes in the form of 2 applications.  This is done because a SwitchYard application will not allow us to define a 'plain Camel' route, it only allows 'SwitchYard-Camel' routes as provided by the SwitchYard Camel component.  In this case, we require a 'plain Camel' route so we can specify a 'dataformats' section.  This route is provided in the second application.


