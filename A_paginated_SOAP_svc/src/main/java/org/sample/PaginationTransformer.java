package org.sample;

import java.io.StringReader;
import java.util.List;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.switchyard.annotations.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class PaginationTransformer {
	
	
	/**
	 * <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sam="http://sample.org/">
   <soapenv:Header/>
   <soapenv:Body>
      <sam:getAlphabetPortion>
         <arg0>1</arg0>
         <arg1>3</arg1>
      </sam:getAlphabetPortion>
   </soapenv:Body>
</soapenv:Envelope>
	 */

	@Transformer(to = "{http://sample.org/}Exception")
	public Element transformExceptionToException(Exception from) {
		// In Production, would want to do something with this...
		return null;
	}

	@Transformer(to = "{http://sample.org/}getAlphabetPortionResponse")
	public Element transformListToGetAlphabetPortionResponse(List from) {
		StringBuffer ackXml = new StringBuffer().append("<sam:getAlphabetPortion xmlns:sam=\"http://sample.org/\">");
		int idx = 0;
		for (Object obj : from) {
			String str = obj.toString();
			ackXml.append("<arg" + idx + ">");
			ackXml.append(str);
			ackXml.append("</arg" + idx + ">");
			idx++;			
		}
		ackXml.append("</sam:getAlphabetPortion>");
	    return toElement(ackXml.toString());

	}

	@Transformer(from = "{http://sample.org/}getAlphabetPortion")
	public PaginationArguments transformGetAlphabetPortionToPaginationArguments(Element from) {
		String arg0 = getElementValue(from, "arg0");
		String arg1 = getElementValue(from, "arg1");
		return new PaginationArguments(Integer.parseInt(arg0),Integer.parseInt(arg1));
	}

	   /**
     * Returns the text value of a child node of parent.
     */
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
