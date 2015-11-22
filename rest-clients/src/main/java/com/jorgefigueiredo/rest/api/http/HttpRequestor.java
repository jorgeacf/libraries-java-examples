package com.jorgefigueiredo.rest.api.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public abstract class HttpRequestor {

    public abstract Response doGet(String url, Iterable<Header> headers) throws IOException;
    public abstract  Uploader startPost(String url, Iterable<Header> headers);
    public abstract  Uploader startPut(String url, Iterable<Header> headers);


    public static class Header {
        private final String key;
        private final String value;

        public Header(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    public static abstract class Uploader {

        private final OutputStream body;

        protected Uploader(OutputStream body) {
            this.body = body;
        }

        public OutputStream getBody() {
            return body;
        }

        public abstract void close();
        public abstract void abort();
        public abstract Response finish();

    }

    public static final class Response {

        private final int statusCode;
        private final InputStream boby;
        private final Map<String, ? extends List<String>> headers;

        public Response(int statusCode, InputStream body, Map<String, ? extends List<String>> headers) {
            this.statusCode = statusCode;
            this.boby = body;
            this.headers = headers;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public InputStream getBoby() {
            return boby;
        }

        public Map<String, ? extends List<String>> getHeaders() {
            return headers;
        }
    }

}
