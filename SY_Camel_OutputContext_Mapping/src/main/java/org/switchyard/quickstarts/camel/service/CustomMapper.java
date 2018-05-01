package org.switchyard.quickstarts.camel.service;

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
import org.switchyard.component.http.composer.HttpBindingData;
import org.switchyard.component.http.composer.HttpContextMapper;

public class CustomMapper extends HttpContextMapper {

	  @Override  
	  public void mapFrom(HttpBindingData source, Context context) throws Exception {  
          super.mapFrom(source, context);
	  }  

	
	
	
	
	@Override  
	  public void mapTo(Context context, HttpBindingData httpData) throws Exception {      
	    // delegate to parent to pick up existing context mapping logic for HTTP
		context.setProperty("content-type", "text/xml", Scope.MESSAGE);
          
	    super.mapTo(context, httpData);  
	  }  
	  
}