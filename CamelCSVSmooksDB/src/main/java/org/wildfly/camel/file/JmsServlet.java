
package org.wildfly.camel.file;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
@WebServlet(name = "HttpServiceServlet", urlPatterns = {"/orders/*"}, loadOnStartup = 1)
public class JmsServlet extends HttpServlet {

    private static final String[] COUNTRIES = {"UK", "US", "Others"};
    private static final Path ORDERS_PATH = new File(System.getProperty("jboss.server.data.dir")).toPath().resolve("orders");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Work out a count of order files processed for each country
        Map<String, Integer> orderCounts = new HashMap<String, Integer>();

        for (String country : COUNTRIES) {
            int orderCount = countOrdersForCountry(country);

            if (orderCount > 0) {
                orderCounts.put(country, orderCount);
            }
        }

        request.setAttribute("orders", orderCounts);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    private int countOrdersForCountry(String country) throws IOException {
        Path countryPath = new File(System.getProperty("jboss.server.data.dir")).toPath().resolve("orders/processed/" + country);
        File file = countryPath.toFile();

        if (file.isDirectory()) {
            return file.list().length;
        }

        return 0;
    }
}
