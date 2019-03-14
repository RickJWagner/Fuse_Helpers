package org.sample.TransformerExample;

import java.util.List;

import org.switchyard.annotations.Transformer;
import org.w3c.dom.Element;

import java.io.StringReader;
import java.util.List;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.switchyard.annotations.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



public final class Transformers {

	@Transformer(to = "{urn:org.sample:TransformerExample:1.0}findBooksByAuthorLastNameResponse")
	public Element transformListToFindBooksByAuthorLastNameResponse(List from) {
		List<Book> result = (List<Book>) from;
		StringBuffer bodyXml = new StringBuffer();
		for (Book b : result) {
			bodyXml
			.append("<Book>")
			.append("<Title>" + b.getTitle() + "</Title>")
			.append("<FirstName>" + b.getAuthorfname() + "</FirstName>")
			.append("<LastName>" + b.getAuthorlname() + "</LastName>")
			.append("</Book>");
		}

		StringBuffer ackXml = new StringBuffer()
		.append("<findBooksByAuthorLastNameResponse xmlns:orders=\"urn:org.sample:TransformerExample:1.0\">")
		.append("<Books>")
		.append(bodyXml)
		.append("</Books>")
		.append("</findBooksByAuthorLastNameResponse>");

		return toElement(ackXml.toString());
	}

	@Transformer(from = "{urn:org.sample:TransformerExample:1.0}findBooksByAuthorLastName")
	public String transformFindBooksByAuthorLastNameToString(Element from) {
		String value = from.getTextContent();
		System.out.println(value);
		return value;
	}
	
    private String getElementValue(Element parent, String elementName) {
        String value = null;
        NodeList nodes = parent.getElementsByTagName(elementName);
        if (nodes.getLength() > 0) {
            value = nodes.item(0).getChildNodes().item(0).getNodeValue();
        }
        return value;
    }
 
    private Element toElement(String xml) {
        DOMResult dom = new DOMResult();
        try {
            TransformerFactory.newInstance().newTransformer().transform(
                    new StreamSource(new StringReader(xml)), dom);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return ((Document)dom.getNode()).getDocumentElement();
}	

}
