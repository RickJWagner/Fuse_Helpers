<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1434653747409" ID="ID_81266199" MODIFIED="1434742218755" TEXT="Docker-WildFly-Camel">
<node CREATED="1434653799059" ID="ID_1355595376" MODIFIED="1434719254609" POSITION="left" TEXT="Startup">
<node CREATED="1434653764495" ID="ID_668294289" MODIFIED="1434653768303" TEXT="Start Docker">
<node CREATED="1434653791348" ID="ID_716864962" MODIFIED="1434653793424" TEXT="docker run --rm -ti -e WILDFLY_MANAGEMENT_USER=admin -e WILDFLY_MANAGEMENT_PASSWORD=admin -p 8080:8080 -p 9990:9990 wildflyext/wildfly-camel"/>
</node>
<node CREATED="1434653821504" ID="ID_427304631" MODIFIED="1434653857455" TEXT="Get Consoles">
<node CREATED="1434653846956" ID="ID_305191077" MODIFIED="1434653855579" TEXT="docker ps (to get Docker Pid)"/>
<node CREATED="1434653858493" ID="ID_1038873543" MODIFIED="1434653873163" TEXT="docker inspect -f &apos;{{ .NetworkSettings.IPAddress }}&apos; (Docker Pid)">
<node CREATED="1434653878920" ID="ID_899123166" MODIFIED="1434653886434" TEXT="Gets you the IP address for consoles"/>
</node>
<node CREATED="1434653889030" ID="ID_606948881" MODIFIED="1434653892805" TEXT="Open ">
<node CREATED="1434653912686" ID="ID_503005870" MODIFIED="1434653914124" TEXT="http://172.17.0.2:8080/hawtio"/>
<node CREATED="1434653929184" ID="ID_618901121" MODIFIED="1434653950990" TEXT="http://172.17.0.2:9990/console"/>
</node>
</node>
</node>
<node CREATED="1434654362649" ID="ID_1762203470" MODIFIED="1434655054412" POSITION="right" TEXT="App Deployment">
<node CREATED="1434654368833" ID="ID_138097337" MODIFIED="1434654376583" TEXT="Can compile on File System, deploy with console"/>
<node CREATED="1434655054412" ID="ID_903132885" MODIFIED="1434655136183" TEXT="Could use Dockerfile">
<node CREATED="1434655086549" ID="ID_1736948845" MODIFIED="1434655090346" TEXT="Dockerfile">
<node CREATED="1434655092733" ID="ID_1960302325" MODIFIED="1434655094255" TEXT="FROM jboss/wildfly   ADD helloworld.war /opt/jboss/wildfly/standalone/deployments/"/>
</node>
<node CREATED="1434655099025" ID="ID_1217638623" MODIFIED="1434655106517" TEXT="Make sure .war is in same dir as Dockerfile"/>
<node CREATED="1434655141574" ID="ID_1895536247" MODIFIED="1434655192321" TEXT="docker build --tag=MYTAG ."/>
<node CREATED="1434655147701" ID="ID_873174243" MODIFIED="1434655185016" TEXT="Later:  docker run -it MYTAG"/>
</node>
</node>
<node CREATED="1434720248379" ID="ID_1259794365" MODIFIED="1434720472060" POSITION="right" TEXT="General Idea">
<node CREATED="1434720258197" ID="ID_132319535" MODIFIED="1434720265686" TEXT="An image is immutable"/>
<node CREATED="1434720268386" ID="ID_592012875" MODIFIED="1434720349283" TEXT="So you need to build a new image to make a change.  (With Dockerfile)"/>
<node CREATED="1434720285308" ID="ID_1018914790" MODIFIED="1434720316258" TEXT="Everything must be done in &apos;batch&apos;.  ">
<node CREATED="1434720305702" ID="ID_486308636" MODIFIED="1434720313374" TEXT="RUN cmd happens at dockerfile build time"/>
<node CREATED="1434720316259" ID="ID_1250906361" MODIFIED="1434720440874" TEXT="CMD cmd happens as the image is started (becomes &apos;container&apos;)"/>
</node>
<node CREATED="1434720357505" ID="ID_20940984" MODIFIED="1434721697627" TEXT="Marek has nice page on options to script changes (batch) including CLI, sed, etc.">
<node CREATED="1434721653685" ID="ID_1879357460" MODIFIED="1434721679998" TEXT="Best:  Use dockerfile &apos;ADD&apos; to put new server.xml, use in cmd line args to run."/>
<node CREATED="1434721698832" ID="ID_530712204" MODIFIED="1434721701192" TEXT="https://github.com/goldmann/wildfly-docker-configuration/tree/master/custom-file"/>
</node>
<node CREATED="1434720472060" ID="ID_1744158118" MODIFIED="1434720487762" TEXT="images can be removed with &apos;docker rmi ImageId&apos;"/>
</node>
<node CREATED="1434742220124" ID="ID_1024949138" MODIFIED="1434742325418" POSITION="left" TEXT="Make custom image">
<node CREATED="1434742227525" ID="ID_982332337" MODIFIED="1434742229228" TEXT="List images: docker images"/>
<node CREATED="1434742241114" ID="ID_1143042312" MODIFIED="1434742241114" TEXT="Remove unwanted image: docker rmi &lt;imageId&gt;"/>
<node CREATED="1434742264247" ID="ID_70986373" MODIFIED="1434742264247" TEXT="Build new image with:  docker build --rm --tag wildfly-camel-debug.2.3.0 /home/rick/Tools/JBoss/WildFlyCamel/WorkAreaForDocker">
<node CREATED="1434742254657" ID="ID_775962921" MODIFIED="1434742291986" TEXT="Need &apos;Dockerfile&apos; in specified dir"/>
</node>
<node CREATED="1434742296163" ID="ID_1719924118" MODIFIED="1434742307185" TEXT="Run with: docker run --rm -ti -e WILDFLY_MANAGEMENT_USER=admin -e WILDFLY_MANAGEMENT_PASSWORD=admin -p 8080:8080 -p 9990:9990 wildfly-camel-debug.2.3.0"/>
<node CREATED="1434742325419" ID="ID_729926674" MODIFIED="1434742328822" TEXT="Sample Dockerfile">
<node CREATED="1434742331760" ID="ID_1205990747" MODIFIED="1434742333046" TEXT="FROM wildflyext/wildfly-camel ADD standalone-camel-DEBUG.xml /opt/jboss/wildfly/standalone/configuration/ # Change the ownership of added files/dirs to `jboss` USER root RUN chown -R jboss.jboss /opt/jboss/wildfly RUN chmod +x /opt/jboss/wildfly/bin/entrypoint.sh USER jboss ENTRYPOINT [&quot;/opt/jboss/wildfly/bin/entrypoint.sh&quot;] CMD [&quot;-c&quot;, &quot;standalone-camel-DEBUG.xml&quot;, &quot;-b&quot;, &quot;0.0.0.0&quot;, &quot;-bmanagement&quot;, &quot;0.0.0.0&quot;]"/>
</node>
</node>
</node>
</map>
