package com.example.switchyard.switchyard_rest_example;

import org.json.JSONObject;
import org.json.XML;

/**
 * Based on
 * {@link} https://github.com/stleary/JSON-java
 */
public class Json2XmlConverter {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static String TEST_XML_STRING = "<test attrib=\"moretest\">Turn this to JSON</test>";

	public static String convertJson2Xml(String str) {
		JSONObject json = new JSONObject(str);
		return XML.toString(json);
	}

	public static String convertXml2Json(String str) {
        JSONObject xmlJSONObj = XML.toJSONObject(str);
        return xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
	}
    
    public static void main(String[] args) {
    	String json = convertXml2Json(TEST_XML_STRING);
    	System.out.println(json);
    	
    	String xml = convertJson2Xml(json);
    	System.out.println(xml);
    }

}
