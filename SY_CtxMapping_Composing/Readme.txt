
Examples:

We can add namespaces through a custom composer, i.e. one that extends SOAPMessageComposer.  The "Decompose" method would prepare the outgoing SOAP, it would look something like  this:

********************
	  public SOAPBindingData decompose(Exchange exchange, SOAPBindingData target) throws Exception {
		  
		  // Add namespaces
		  
		  SOAPEnvelope env = target.getSOAPMessage().getSOAPPart().getEnvelope();
		  env.addNamespaceDeclaration("platformA", "urn:sample.stuff.com");
		  env.addNamespaceDeclaration("platformMsgs", "urn:sample.other.stuff.com");
		  env.addNamespaceDeclaration("docStuff", "urn:even.more.sample.stuff.com");
		  
		  SOAPBindingData sbd = super.decompose(exchange, target);		  
		  return sbd;
	  }

***********************


The SOAP header elements can be manipulated through a custom ContextMapper (again, extending SOAPContextMapper this time).  Here's a sample:

***********************

	  public void mapTo(Context context, SOAPBindingData soapData) throws Exception {
          
          QName passport = new QName("urn:sample.stuff.com", "thingy", "platformA");
          QName passportType = new QName("http://www.w3.org/2001/XMLSchema-instance", "type", "xsi");
          QName mail = new QName("http://www.w3.org/2001/XMLSchema-instance", "mail", "xsi");
          
          
          SOAPHeaderElement headerElement = soapData.getSOAPMessage().getSOAPHeader().addHeaderElement(passport);
          headerElement.addAttribute(thingyType, "Thingy");
          
          Element mailChild = headerElement.addChildElement(mail);
           
	    super.mapTo(context, soapData);

	  }

***********************


In switchyard.xml, we name the custom classes and treat the payload as XML.  Here's a snippet:

    <sca:reference name="ReverseService" multiplicity="0..1" promote="ProxyService/ReverseService">
      <sca:interface.wsdl interface="META-INF/ReverseService.wsdl#wsdl.porttype(ReverseService)"/>
      <soap:binding.soap>
        <soap:contextMapper class="com.example.SoapCustomMapper" includes=".*" soapHeadersType="VALUE"/>
        <soap:messageComposer class="com.example.SoapCustomComposer" unwrapped="false"/>
        <soap:wsdl>META-INF/ReverseService.wsdl</soap:wsdl>
        <soap:endpointAddress>http://localhost:${soapClientPort}/ReverseService</soap:endpointAddress>
      </soap:binding.soap>
    </sca:reference>
