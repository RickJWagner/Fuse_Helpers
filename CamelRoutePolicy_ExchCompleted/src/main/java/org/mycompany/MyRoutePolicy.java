package org.mycompany;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.camel.support.RoutePolicySupport;

public class MyRoutePolicy extends RoutePolicySupport {

		@Override
		public void onExchangeDone(Route route, Exchange exchange) {
			System.out.println("### Exchange is done! " + exchange.toString() + " Route:" + route.getId());
			
	    /**		
		CamelContext context = exchange.getContext();
		try {
		exchange.getContext().getInflightRepository().remove(exchange);
		context.stopRoute(stop);
		context.startRoute(start);
		} catch (Exception e) {
		getExceptionHandler().handleException(e);
		}
		**/
		}
		}