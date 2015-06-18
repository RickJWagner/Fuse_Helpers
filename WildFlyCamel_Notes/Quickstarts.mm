<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1433622152693" ID="ID_688101220" MODIFIED="1433622246067" TEXT="Camel-on-EAP-quickstarts">
<node CREATED="1433622174731" ID="ID_1634986055" MODIFIED="1434655252054" POSITION="right" TEXT="camel-activemq">
<node CREATED="1433622387245" ID="ID_608302950" MODIFIED="1433623276342" TEXT="RB: ActiveMQRouteBuilder">
<node CREATED="1433622538365" ID="ID_496878923" MODIFIED="1433622542124" TEXT="@Startup @ApplicationScoped @ContextName(&quot;amq-cdi-context&quot;)"/>
<node CREATED="1433622551019" ID="ID_1314929082" MODIFIED="1433622591337" TEXT="makes A-MQ component in &apos;configure()&apos;"/>
<node CREATED="1433623276342" ID="ID_1680459029" MODIFIED="1433623301270" TEXT="So just uses &apos;startup&apos; and sticks in CDI, the route runs (no other startup references)"/>
</node>
<node CREATED="1434655252055" ID="ID_1337495953" MODIFIED="1434655267088" TEXT="Unsupported!  A-MQ is embedded">
<icon BUILTIN="closed"/>
</node>
</node>
<node CREATED="1433622248905" FOLDED="true" ID="ID_249526027" MODIFIED="1433860898966" POSITION="left" TEXT="camel-cdi">
<node CREATED="1433794216060" ID="ID_1251082217" MODIFIED="1433794219030" TEXT="@Startup @ApplicationScoped @ContextName(&quot;amq-cdi-context&quot;)"/>
<node CREATED="1433794221260" ID="ID_834220004" MODIFIED="1433794256371" TEXT="Servlet Injects Camel Context from CDI, uses Producer to &quot;direct&quot;"/>
<node CREATED="1433795085289" ID="ID_48983893" MODIFIED="1433795090868" TEXT="Servlet uses: @Inject &#x9;@ContextName(&quot;cdi-context&quot;) &#x9;private CamelContext camelctx;"/>
<node CREATED="1433795218583" ID="ID_1069272234" MODIFIED="1433795230339" TEXT="Test class uses simple Http methods to act like client"/>
</node>
<node CREATED="1433622253825" ID="ID_1856674766" MODIFIED="1434655494833" POSITION="right" TEXT="camel-cxf">
<node CREATED="1433726315789" ID="ID_1161271581" MODIFIED="1433726401295" TEXT="Wildfly-Camel does not support cxf consumers!">
<icon BUILTIN="yes"/>
<node CREATED="1433726553076" ID="ID_1111744755" MODIFIED="1433794371593" TEXT="Camel-proxy might help">
<icon BUILTIN="full-1"/>
</node>
</node>
<node CREATED="1433726886976" ID="ID_1111811357" MODIFIED="1433726914121" TEXT="Has Svlt">
<node CREATED="1433726894770" ID="ID_175619540" MODIFIED="1433726901286" TEXT="Injects CamelContext"/>
<node CREATED="1433726904519" ID="ID_1815181208" MODIFIED="1433726910821" TEXT="Uses &apos;direct&apos; producertemplate"/>
<node CREATED="1433726914121" ID="ID_49262445" MODIFIED="1433726931990" TEXT="Expects Result from direct invoc."/>
</node>
<node CREATED="1433726936316" ID="ID_899933667" MODIFIED="1433727020670" TEXT="Has CamelCtxt">
<node CREATED="1433726965008" ID="ID_308545707" MODIFIED="1433726966999" TEXT="@Startup @ApplicationScoped @ContextName"/>
<node CREATED="1433726971896" ID="ID_644971845" MODIFIED="1433726981435" TEXT="from(direct)to(cxf)"/>
<node CREATED="1433727023202" ID="ID_1525991125" MODIFIED="1433727030807" TEXT="no explicit return, but is a String"/>
</node>
<node CREATED="1433727006167" ID="ID_13547497" MODIFIED="1433727015478" TEXT="Has WS (Not very fancy)"/>
<node CREATED="1434655494833" ID="ID_211250098" MODIFIED="1434655511842" TEXT="Provides web form to initiate testing.">
<icon BUILTIN="ksmiletris"/>
</node>
</node>
<node CREATED="1433622267809" FOLDED="true" ID="ID_271699591" MODIFIED="1433860904295" POSITION="left" TEXT="camel-jaxws">
<node CREATED="1433794350474" ID="ID_100214605" MODIFIED="1433794377225" TEXT="Uses CamelProxy to mimic Camel JAX-WS consumer">
<icon BUILTIN="full-1"/>
</node>
<node CREATED="1433794460655" ID="ID_1192320166" MODIFIED="1433794470409" TEXT="Servlet makes JaxWS client in a few lines of code"/>
<node CREATED="1433794721972" ID="ID_1936896086" MODIFIED="1433794732993" TEXT="Routebuilder as usual (named, startup, ...)"/>
<node CREATED="1433794737964" ID="ID_23594391" MODIFIED="1433794812711" TEXT="Camel route consumes &apos;direct&apos;, implements the WS methods"/>
<node CREATED="1433794904485" ID="ID_1009816200" MODIFIED="1433794914675" TEXT="WSImpl delegates to Camel through:  @Produce(uri=&quot;direct:start&quot;)     GreetingService greetingService;"/>
<node CREATED="1433795003055" ID="ID_1615702842" MODIFIED="1433795018702" TEXT="Main Idea:  Camel Route stands in for called WS Impl."/>
</node>
<node CREATED="1433622273299" ID="ID_36900856" MODIFIED="1433622277654" POSITION="right" TEXT="camel-jpa"/>
<node CREATED="1433622279634" FOLDED="true" ID="ID_1751184868" MODIFIED="1433964946703" POSITION="left" TEXT="camel-jms">
<node CREATED="1433872377084" ID="ID_1160272963" MODIFIED="1433872380482" TEXT="Usual: @Startup @ApplicationScoped @ContextName(&quot;jms-cdi-context&quot;)"/>
<node CREATED="1433872385376" ID="ID_143665609" MODIFIED="1433964855826" TEXT="RB injects CF:     @Resource(mappedName = &quot;java:jboss/DefaultJMSConnectionFactory&quot;)     private ConnectionFactory connectionFactory;"/>
<node CREATED="1433872417118" ID="ID_1031277775" MODIFIED="1433872446830" TEXT="RB makes JMSComponent, injects:  getContext().addComponent(&quot;jms&quot;, component);"/>
<node CREATED="1433872475603" ID="ID_647662136" MODIFIED="1433872487194" TEXT="Sends, reads from injected JMSComponent"/>
</node>
<node CREATED="1433622294836" ID="ID_1801058753" MODIFIED="1434656525704" POSITION="right" TEXT="camel-mail">
<node CREATED="1434655519372" ID="ID_1092210388" MODIFIED="1434655528255" TEXT="Provides web form to initiate testing">
<icon BUILTIN="ksmiletris"/>
</node>
<node CREATED="1434655532100" ID="ID_1036299736" MODIFIED="1434655540622" TEXT="configures &apos;green mail&apos; for example"/>
<node CREATED="1434656527168" ID="ID_1692876453" MODIFIED="1434656800435" TEXT="Wants you to run CLI scripts to bind mail server">
<node CREATED="1434656803500" ID="ID_1293947943" MODIFIED="1434656822182" TEXT="So not convenient w/Docker">
<icon BUILTIN="smiley-neutral"/>
</node>
</node>
</node>
<node CREATED="1433622300230" ID="ID_886907030" MODIFIED="1433966041925" POSITION="left" TEXT="camel-rest">
<node CREATED="1433964842243" ID="ID_1319171785" MODIFIED="1433964859558" TEXT="CXFRS and Restlet component consumer endpoints are not supported">
<icon BUILTIN="yes"/>
<node CREATED="1433964924412" ID="ID_812611728" MODIFIED="1433964936637" TEXT="Instead use Camel REST DSL and Camel Proxy"/>
</node>
<node CREATED="1433966043500" ID="ID_1127980988" MODIFIED="1433966048343" TEXT="Needs more study"/>
</node>
<node CREATED="1433622304755" ID="ID_175123175" MODIFIED="1433622308493" POSITION="right" TEXT="camel-transacted-jms"/>
</node>
</map>
