package com.example;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;

import org.switchyard.Context;
import org.switchyard.Property;
import org.switchyard.Scope;
import org.switchyard.component.common.composer.BindingData;
import org.switchyard.config.model.composer.ContextMapperModel;
import org.switchyard.component.soap.composer.SOAPBindingData;
import org.switchyard.component.soap.composer.SOAPContextMapper; 

public class SoapCustomMapper extends SOAPContextMapper {

	  @Override  
	  public void mapFrom(SOAPBindingData source, Context context) throws Exception {  
		  
		  // We insert the desired header onto the SOAPBindingData before the parent mapper 
		  // carries values forward.  
		  
          QName arbitraryQname = new QName("http://AnExample", "arbitraryElement");
          SOAPHeaderElement headerElement = source.getSOAPMessage().getSOAPHeader().addHeaderElement(arbitraryQname);
          headerElement.addTextNode("ArbitraryValue");

          super.mapFrom(source, context);
	  }  

	
	
	
	
	@Override  
	  public void mapTo(Context context, SOAPBindingData soapData) throws Exception {  
		    
	    // delegate to parent to pick up existing context mapping logic for SOAP  
	    super.mapTo(context, soapData);  
	  
	  }  
	  
}
