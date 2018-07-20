package org.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JMSClient
 */

@WebServlet("/ConsumerServlet")
public class ConsumerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Queue testq1 set up directly in standalone.xml (copied from
	// standalone-full.xml)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String destinationName = "java:jboss/exported/jms/test";
		PrintWriter out = response.getWriter();
		Context ic = null;
		ConnectionFactory cf = null;
		Connection connection = null;

		try {
			ic = new InitialContext();

			//cf = (ConnectionFactory) ic.lookup("/ConnectionFactory");
			cf = (ConnectionFactory)ic.lookup("java:/jms/RicksConnectionFactory");
			Queue queue = (Queue) ic.lookup(destinationName);

			connection = cf.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			MessageConsumer messageConsumer = session.createConsumer(queue);

			connection.start();

			TextMessage messageReceived = (TextMessage) messageConsumer
					.receive(5000);

			System.out.println("Received message: " + messageReceived.getText());
			out.println("Received message: " + messageReceived.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}