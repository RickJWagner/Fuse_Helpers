
This project demonstrates use of REST DSL with SSL.

Note the restConfiguration for the Camel context.  (Also note it says '8080', but Undertow redirects to port 8443 for us.)

The application can be accessed with a url like this:   https://localhost:8443/test/hello/1

On first use, the server may provide a message as it self-configures for SSL:

2018-10-24 11:02:09,850 WARN  [org.jboss.as.domain.management.security] (MSC service thread 1-4) WFLYDM0111: Keystore /home/user/Tools/A/Fuse7.1/jboss-eap-7.1.0/jboss-eap-7.1/standalone/configuration/application.keystore not found, it will be auto generated on first use with a self signed certificate for host localhost

