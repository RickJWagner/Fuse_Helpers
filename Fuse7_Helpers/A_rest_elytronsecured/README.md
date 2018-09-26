Camel REST security sample

* * * Note:  At the time of this writing (26 Sept 2018) there is a problem with some of the artifacts provided for the Fuse EAP quickstarts.  To work around these issues, use the following flag with Maven:

mvn clean package -Dversion.fuse=7.1.0.fuse-710019-redhat-00002            // Use -Dversion.fuse for all maven commands

* * *  


This sample application demonstrates how Elytron security can be used with a Camel REST application.   It is based on the combination of two existing quickstarts, EAP's "servlet-security" and Fuse 7.1's "camel-rest-swagger".  (Use those two for reference if you wish to more closely study things.)

If you have trouble running this quickstart, please 'regress' one step and run the EAP quickstart 'servlet-security' first.  That quickstart will have more direct instructions, successful use of 'servlet-security' will also ensure server setup is properly done.


-------
When you deploy this example, two users are automatically created for you: user `quickstartUser` with password `quickstartPwd1!` and user `guest` with password `guestPwd1!`. This data is located in the `src/main/resources/import.sql` file.

This quickstart takes the following steps to implement Servlet security:

1. Defines a security domain in the `standalone.xml` configuration file using the Database JAAS LoginModule.
2. Adds an application user with access rights to the application.

        User Name: quickstartUser
        Password: quickstartPwd1!
        Role: quickstarts

3. Adds another user with no access rights to the application.

        User Name: guest
        Password: guestPwd1!
        Role: notauthorized

4. Adds a security domain reference to `WEB-INF/jboss-web.xml`.
5. Adds a security constraint to the `WEB-INF/web.xml` .
6. Adds a security annotation to the Servlet declaration.

Please note the allowed user role `quickstarts` in the annotation `@RolesAllowed` is the same as the user role defined in step 2.

_Note: This quickstart uses the H2 database included with Red Hat JBoss Enterprise Application Platform 7.1. It is a lightweight, relational example datasource that is used for examples only. It is not robust or scalable, is not supported, and should NOT be used in a production environment!_

_Note: This quickstart uses a `*-ds.xml` datasource configuration file for convenience and ease of database configuration. These files are deprecated in JBoss EAP and should not be used in a production environment. Instead, you should configure the datasource using the Management CLI or Management Console. Datasource configuration is documented in the [Configuration Guide](https://access.redhat.com/documentation/en/red-hat-jboss-enterprise-application-platform/) for Red Hat JBoss Enterprise Application Platform._

------


## Configure the Server

You can configure the security domain by running JBoss CLI commands. For your convenience, this quickstart batches the commands into a `configure-security-domain.cli` script provided in the root directory of this quickstart.

1. Before you begin, back up your server configuration file
    * If it is running, stop the JBoss EAP server.
    * Backup the file: `EAP7_HOME/standalone/configuration/standalone.xml`
    * After you have completed testing this quickstart, you can replace this file to restore the server to its original configuration.

2. Start the JBoss EAP server by typing the following:

        For Linux:  EAP7_HOME/bin/standalone.sh
        For Windows:  EAP7_HOME\bin\standalone.bat

3. Review the `configure-security-domain.cli` file in the root of this quickstart directory. This script adds the `servlet-security-quickstart` security domain to the `security` subsystem in the server configuration and configures authentication access.

4. Open a new command prompt, navigate to the root directory of this quickstart, and run the following command, replacing EAP7_HOME with the path to your server:

        For Linux: EAP7_HOME/bin/jboss-cli.sh --connect --file=configure-security-domain.cli
        For Windows: EAP7_HOME\bin\jboss-cli.bat --connect --file=configure-security-domain.cli
You should see the following result when you run the script:

        The batch executed successfully

--- 

## Review the Modified Server Configuration

After stopping the server, open the `EAP7_HOME/standalone/configuration/standalone.xml` file and review the changes.

The following `servlet-security-quickstart` security-domain element was added to the `security` subsystem.

      	<security-domain name="servlet-security-quickstart" cache-type="default">
    	      <authentication>
          	    <login-module code="Database" flag="required">
            	      <module-option name="dsJndiName" value="java:jboss/datasources/ServletSecurityDS"/>
                    <module-option name="principalsQuery" value="SELECT PASSWORD FROM USERS WHERE USERNAME = ?"/>
                    <module-option name="rolesQuery" value="SELECT R.NAME, 'Roles' FROM USERS_ROLES UR INNER JOIN ROLES R ON R.ID = UR.ROLE_ID INNER JOIN USERS U ON U.ID = UR.USER_ID WHERE U.USERNAME = ?"/>
                </login-module>
            </authentication>
        </security-domain>

Please note that the security domain name `servlet-security-quickstart` must match the one defined in the `/src/main/webapp/WEB-INF/jboss-web.xml` file.

---

## Start the Server, build and deploy this quickstart

1. Start the server

2.  From this directory, build the quickstart: 

mvn clean package -Dversion.fuse=7.1.0.fuse-710019-redhat-00002 

------

## Prove Elytron security by accessing the Servlet Application

The application will be running at the following URL <http://localhost:8080/servlet-security/>.

When you access the application, you should get a browser login challenge.

Log in using the username `quickstartUser` and password `quickstartPwd1!`. The browser will display the following security info:

    Successfully called Secured Servlet

    Principal : quickstartUser
    Remote User : quickstartUser
    Authentication Type : BASIC

Now close the browser. Open a new browser and log in with username `guest` and password `guestPwd1!`. The browser will display the following error:

    Forbidden


## Server Log: Expected Warnings and Errors

_Note:_ You may see the following warnings in the server log. You can ignore these warnings.

    WFLYJCA0091: -ds.xml file deployments are deprecated. Support may be removed in a future version.

    HHH000431: Unable to determine H2 database version, certain features may not work


---

## Prove Elytron security by accessing the REST Application

1. Browse to http://localhost:8080/example-camel-rest-swagger/
2. Security should work just as the Servlet security did


