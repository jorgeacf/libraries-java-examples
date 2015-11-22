package com.jorgefigueiredo.jetty;

import junit.framework.TestSuite;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class MainTest extends TestSuite {

    @Test
    public void testMainApp() throws Exception {

        new Thread(new Runnable() {
            public void run() {
                try {
                    Main.main(new String[] {});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();



        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            HttpGet httpget = new HttpGet("http://localhost:8080/");

            ResponseHandler<String> responseHandler1 = new ResponseHandler<String>() {

                public String handleResponse(HttpResponse httpResponse) throws IOException {

                    int status = httpResponse.getStatusLine().getStatusCode();

                    if(status >= 200 && status <= 300) {
                        HttpEntity httpEntity = httpResponse.getEntity();

                        return httpEntity != null ? EntityUtils.toString(httpEntity) : null;
                    }
                    else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            String body1 = httpClient.execute(httpget, responseHandler1);
            System.out.println(body1);

            HttpPost httppost = new HttpPost("http://localhost:8080/");

            ResponseHandler<String> responseHandler2 = new ResponseHandler<String>() {

                public String handleResponse(HttpResponse httpResponse) throws IOException {

                    int status = httpResponse.getStatusLine().getStatusCode();

                    if(status >= 200 && status <= 300) {
                        HttpEntity httpEntity = httpResponse.getEntity();

                        return httpEntity != null ? EntityUtils.toString(httpEntity) : null;
                    }
                    else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            String body2 = httpClient.execute(httppost, responseHandler2);
            System.out.println(body2);

        }
        finally {
            httpClient.close();
        }

    }

}
