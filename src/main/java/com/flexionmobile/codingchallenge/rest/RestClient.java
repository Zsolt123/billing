package com.flexionmobile.codingchallenge.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class RestClient {

    private static final String HOST_URL = "http://sandbox.flexionmobile.com/javachallenge/rest";
    private static final String DEVELOPER_URL = "/developer";
    private static final String DEVELOPER_ID_URL = "/zsolt123";
    private static final String URL = HOST_URL + DEVELOPER_URL + DEVELOPER_ID_URL;

    private static final String GET = "GET";
    private static final String POST = "POST";

    private static final String ERROR_COULD_NOT_EXECUTE_REST_API_REQUEST = "Could not execute REST API request: %s";
    private static final String ERROR_REST_API_REQUEST_FAILED = "REST API request failed with %d HTTP code.";

    public static JsonNode get(final String path) {

        return sendRequest(path, GET);
    }

    public static JsonNode post(final String path) {

        return sendRequest(path, POST);
    }

    private static JsonNode sendRequest(final String path, final String method) {

        JsonNode responseJson;

        final String urlPath = URL + path;
        try {
            final URL url = new URL(urlPath);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);

            final int responseCode = conn.getResponseCode();
            if (responseCode == 200) {

                final ObjectMapper mapper = new ObjectMapper();

                responseJson = mapper.readTree(conn.getInputStream());
            } else {

                final String errorMessage = String.format(ERROR_REST_API_REQUEST_FAILED,
                        responseCode);
                System.err.println(errorMessage);

                responseJson = JsonNodeFactory.instance.nullNode();
            }
            conn.disconnect();

        } catch (final IOException e) {

            final String errorMessage = String.format(ERROR_COULD_NOT_EXECUTE_REST_API_REQUEST,
                    urlPath);
            System.err.println(errorMessage);

            responseJson = JsonNodeFactory.instance.nullNode();
        }

        return responseJson;
    }
}
