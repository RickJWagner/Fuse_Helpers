package org.wildfly.camel.examples.cxf.jaxws;

import org.sample.TransferRequest;
import org.sample.TransferResponse;

public class OrderProcessor {
	
	
	public TransferResponse processTransfer(TransferRequest req) {
		System.out.println("OrderProcessor:processTransfer runs!");
		TransferResponse resp = new TransferResponse();
		resp.setReply("A-OK");
		return resp;
	}

}
