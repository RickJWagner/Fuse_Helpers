package com.example.switchyard.rest_rules;

// Invoke this with http://localhost:8080/GGL/order/1

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/order")
public interface OrderResource {

    @GET
    @Path("{orderId}")
    @Produces({"text/xml"})
    public Order getOrder(@PathParam("orderId") Long orderId);

}