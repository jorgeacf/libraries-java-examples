package com.jorgefigueiredo.rest.api.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StandardHttpRequestor extends HttpRequestor {



    @Override
    public Response doGet(String url, Iterable<Header> headers) throws IOException {

        HttpURLConnection conn = prepRequest(url, headers);
        conn.setRequestMethod("GET");
        conn.connect();

        return toResponse(conn);
    }

    private Response toResponse(HttpURLConnection conn) throws IOException {

        int responseCode = conn.getResponseCode();

        InputStream bodyStream;

        if(responseCode >= 400) {
            bodyStream = conn.getErrorStream();
        }
        else {
            bodyStream = conn.getInputStream();
        }

        return new Response(conn.getResponseCode(), bodyStream, conn.getHeaderFields());
    }

    @Override
    public Uploader startPost(String url, Iterable<Header> headers) {
        return null;
    }

    @Override
    public Uploader startPut(String url, Iterable<Header> headers) {
        return null;
    }

    private HttpURLConnection prepRequest(String url, Iterable<Header> headers) {

        URL urlObject = null;
        try {
            urlObject = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(String.format("The Url %s is not valid.", url));
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) urlObject.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        for(Header header : headers) {
            conn.addRequestProperty(header.getKey(), header.getValue());
        }

        return conn;
    }

}
