package org.mycompany;

import java.util.Date;
import java.util.EventObject;

import org.apache.camel.Exchange;
import org.apache.camel.management.EventNotifierSupport;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeSentEvent;

public class AuditEventNotifier extends EventNotifierSupport {

public void notify(EventObject event) throws Exception {
	
/**	
  if (event instanceof ExchangeSentEvent) {
      ExchangeSentEvent sent = (ExchangeSentEvent) event;
      log.info(">>> Took " + sent.getTimeTaken() + " millis to send to external system : " + sent.getEndpoint());
  }
  **/

  if (event instanceof ExchangeCompletedEvent) {;
      ExchangeCompletedEvent exchangeCompletedEvent = (ExchangeCompletedEvent) event;
      System.out.println("### exchange completed event:" + exchangeCompletedEvent.toString());
      Object corrId = exchangeCompletedEvent.getExchange().getProperty(Exchange.CORRELATION_ID);
      if (null == corrId) {
    	  corrId = "It was null";
      }
      System.out.println("### corrId:" + corrId);
      
      /**
      Exchange exchange = exchangeCompletedEvent.getExchange();
      String routeId = exchange.getFromRouteId();
      Date created = ((ExchangeCompletedEvent) event).getExchange().getProperty(Exchange.CREATED_TIMESTAMP, Date.class);
      // calculate elapsed time
      Date now = new Date();
      long elapsed = now.getTime() - created.getTime();
      log.info(">>> Took " + elapsed + " millis for the exchange on the route : " + routeId);
      **/
  }
}

public boolean isEnabled(EventObject event) {
  return true;
}

protected void doStart() throws Exception {
  // filter out unwanted events
  setIgnoreCamelContextEvents(true);
  setIgnoreServiceEvents(true);
  setIgnoreRouteEvents(true);
  setIgnoreExchangeCreatedEvent(true);
  setIgnoreExchangeCompletedEvent(false);
  setIgnoreExchangeFailedEvents(true);
  setIgnoreExchangeRedeliveryEvents(true);
  setIgnoreExchangeSentEvents(false);
}

protected void doStop() throws Exception {
  // noop
}

}
