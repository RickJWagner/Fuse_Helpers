package org.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JMSClient
 */


@WebServlet("/ProducerServlet")
public class ProducerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
// Queue testq1 set up directly in standalone.xml (copied from standalone-full.xml)
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String destinationName = "java:jboss/exported/jms/test";
	     PrintWriter out = response.getWriter();
	     Context ic = null;
	     ConnectionFactory cf = null;
	     Connection connection =  null;
	     
			 String myarg = request.getParameter("myarg");
			 if (null == myarg){
				 myarg = "Default MyArg";
			 }
	     System.out.println("myarg:" + myarg);

	     try {         
	       ic = new InitialContext();

				 //cf = (ConnectionFactory)ic.lookup("/ConnectionFactory");
				 cf = (ConnectionFactory)ic.lookup("java:/jms/RicksConnectionFactory");
	       Queue queue = (Queue)ic.lookup(destinationName);

	       connection = cf.createConnection();
	       Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	       MessageProducer publisher = session.createProducer(queue);
	 
	       connection.start();

	       //TextMessage message = session.createTextMessage("Hello AS 7 !");
	       TextMessage message = session.createTextMessage(myarg);
	       
	       publisher.send(message);

	       out.println("Message sento to the JMS Provider");

	    }
	     catch (Exception exc) {
	       exc.printStackTrace();
	     }
	    finally {         


	      if (connection != null)   {
	        try {
	           connection.close();
	        } catch (JMSException e) {                    
	          e.printStackTrace();
	        }
	    } 
	  }
	 }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     doGet(request, response);
	   }
}