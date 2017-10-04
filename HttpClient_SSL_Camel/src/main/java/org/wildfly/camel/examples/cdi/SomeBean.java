package org.wildfly.camel.examples.cdi;

import java.io.IOException;

import javax.inject.Named;


import javax.net.ssl.SSLContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.SystemDefaultHttpClient;
// import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;



@Named("someBean")
public class SomeBean {

    public String someMethod2() throws Exception {
        return this.someMethod("AMessage!");
    }

    public String someMethod(String message) throws Exception {
    	System.out.println("### About to try an HttpClient call. ###");
    	String returnedFromHttpClient = null;
    	returnedFromHttpClient = httpClientGet();
    	System.out.println("### Just got back from HttpClient GET:" + returnedFromHttpClient + " ###");
    	
    	returnedFromHttpClient = httpClientPost();
    	System.out.println("### Just got back from HttpClient POST:" + returnedFromHttpClient + " ###");
    	
    	System.out.println("### Done with Bean making call!  ###");
        return "Hello " + message;
    }
    

    private String httpClientPost() throws ClientProtocolException, IOException {
    	HttpClient hc = new SystemDefaultHttpClient();
    	HttpPost postReq = new HttpPost("https://localhost:8543/Protected_REST_App/rest/message/post/somevalue2");
        addHeaders(postReq);
        HttpResponse hrResponse = hc.execute(postReq);
        String sContent = getContent(hrResponse);
        System.out.println("### sContent is:" + sContent + "###");
        return sContent;
    }
    
    

    
    private String httpClientGet() throws ClientProtocolException, IOException {
    	HttpClient hc = new SystemDefaultHttpClient();
    	HttpGet hgRequest = new HttpGet("https://localhost:8543/Protected_REST_App/rest/message/somevalue1");
        addHeaders(hgRequest);
        HttpResponse hrResponse = hc.execute(hgRequest);
        String sContent = getContent(hrResponse);
        System.out.println("### sContent is:" + sContent + "###");
        return sContent;
    }
    
    private String getContent(final HttpResponse hrResponse) throws IllegalStateException, IOException {
            StringBuilder sbResult = new StringBuilder();
            try {
                final int nStatusCode = hrResponse.getStatusLine().getStatusCode();
                sbResult.append("Status Code: ");
                sbResult.append(nStatusCode);
                sbResult.append("\r\n");
                if (nStatusCode < 300) {
                    sbResult.append("Response:\r\n");
                    BufferedReader brReader = new BufferedReader(new InputStreamReader(hrResponse.getEntity().getContent()));
                    String sline;
                    while ((sline = brReader.readLine()) != null) {
                      if (sbResult.length() <= 100000) {
                          sbResult.append(sline);
                      }
                    }
                }
            }finally {
            	System.out.println("Finally!");
            	return sbResult.toString();
            }
    }
    
    
    private void addHeaders(final HttpRequestBase heerbRequest) {
        heerbRequest.addHeader("Connection", "keep-alive");
        heerbRequest.addHeader("Cache-Control", "no-cache");
        heerbRequest.addHeader("Pragma", "no-cache");
        heerbRequest.addHeader("User-Agent", "Java/1.8.0_112");
        heerbRequest.addHeader("Accept", "text/html, image/gif, image/jpeg, *");
    }  
    
       
}
