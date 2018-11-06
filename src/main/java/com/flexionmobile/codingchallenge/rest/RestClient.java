package com.flexionmobile.codingchallenge.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

    private static final String HOST_URL = "http://sandbox.flexionmobile.com/javachallenge/rest";
    private static final String DEVELOPER_URL = "/developer";
    private static final String DEVELOPER_ID_URL = "/zsolt123";
    private static final String URL = HOST_URL + DEVELOPER_URL + DEVELOPER_ID_URL;

    private static final String GET = "GET";
    private static final String POST = "POST";

    public static void get(final String path) {

        send(path, GET);
    }

    public static void post(final String path) {

        send(path, POST);
    }

    private static void send(final String path, final String method) {

        try {
            final URL url = new URL(URL + path);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.connect();

        } catch (final IOException e) {
            System.err.println("Could not connect to " + URL + path);
        }
    }
}
