package com.example.switchyard.SY_Properties_Setter;

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
	   
		String my_content_type = context.getProperty("myText", Scope.MESSAGE).getValue().toString();
		System.out.println("###" +  my_content_type);
		context.setProperty("content-type", my_content_type, Scope.MESSAGE);
		// delegate to parent to pick up existing context mapping logic for HTTP  
	    super.mapTo(context, httpData);  
	  }  
	  
}