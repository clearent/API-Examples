package com.clearent.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Transaction {

    private static final String API_URI = "https://gateway-sb.clearent.net/rest/v2/transactions";
    private static final String API_KEY = "api key goes here";
    private static final String ACCEPT_HEADER_KEY = "Accept";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String POST_METHOD = "POST";
    private static final boolean OUTPUT_TRUE = true;

    public static void main(String[] args) throws Exception {
        String response;

        // sale --
        System.out.println("Beginning Sale Request");
        response = requestSale();
        System.out.println(response);

        // authorization --
        System.out.println("Beginning Authorization Request");
        response = requestAuth();
        System.out.println(response);

        // capture
        System.out.println("Beginning Capture Request");
        response = requestCapture();
        System.out.println(response);

        // forced sale --
        System.out.println("Beginning Forced Request");
        response = requestForcedSale();
        System.out.println(response);

        // refund
        System.out.println("Beginning Refund Request");
        response = requestRefund();
        System.out.println(response);

        // void
        System.out.println("Beginning Void Request");
        response = requestVoid();
        System.out.println(response);
    }

    public static String requestSale() throws Exception {
        final String card = "4111111111111111";
        final String exp_date = "1219";
        final String amount = "25.00";
        final String requestBody = "{\"type\":\"SALE\",\"card\":\"" + card
                + "\",\"exp-date\":\"" + exp_date + "\",\"amount\":\"" + amount
                + "\"}";
        return requestTransaction(requestBody);
    }

    public static String requestAuth() throws Exception {
        final String card = "4111111111111111";
        final String exp_date = "1219";
        final String amount = "25.00";
        final String requestBody = "{\"type\":\"AUTH\",\"card\":\"" + card
                + "\",\"exp-date\":\"" + exp_date + "\",\"amount\":\"" + amount
                + "\"}";
        return requestTransaction(requestBody);
    }

    public static String requestCapture() throws Exception {
        final String id = "12345";
        final String amount = "25.00";
        final String requestBody = "{\"type\":\"CAPTURE\",\"id\":\"" + id
                + "\",\"amount\":\"" + amount + "\"}";
        return requestTransaction(requestBody);
    }

    public static String requestForcedSale() throws Exception {
        final String card = "4111111111111111";
        final String exp_date = "1219";
        final String amount = "25.00";
        final String order_id = "12345";
        final String authorization_code = "APPC10";
        final String requestBody = "{\"type\":\"FORCED SALE\",\"card\":\""
                + card + "\",\"exp-date\":\"" + exp_date + "\",\"amount\":\""
                + amount + "\",\"order-id\":\"" + order_id
                + "\",\"authorization-code\":\"" + authorization_code + "\"}";
        return requestTransaction(requestBody);
    }

    public static String requestRefund() throws Exception {
        final String card = "4111111111111111";
        final String amount = "25.00";
        final String requestBody = "{\"type\":\"REFUND\",\"card\":\"" + card
                + "\",\"amount\":\"" + amount + "\"}";
        return requestTransaction(requestBody);
    }

    public static String requestVoid() throws Exception {
        final String id = "12345";
        final String requestBody = "{\"type\":\"VOID\",\"id\":\"" + id + "\"}";
        return requestTransaction(requestBody);
    }

    private static String requestTransaction(String requestBody)
            throws IOException {

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = setupHttpConnection(API_URI);
            setRequestParamters(httpConnection, API_URI);
            sendRequest(requestBody, httpConnection.getOutputStream());
            return getResponse(httpConnection.getInputStream());
        } catch (IOException e) {
            return getResponse(httpConnection.getErrorStream());
        } catch (Exception e) {
            return getResponse(httpConnection.getErrorStream());
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
    }

    private static HttpURLConnection setupHttpConnection(
            final String apiEndpoint) {
        final URL url = createUrl(apiEndpoint);
        return openHttpConnection(url);
    }

    private static HttpURLConnection openHttpConnection(final URL url) {
        try {
            final HttpURLConnection httpConnection = (HttpURLConnection) url
                    .openConnection();
            httpConnection.setDoOutput(OUTPUT_TRUE);
            return httpConnection;
        } catch (IOException ioe) {
            throw new IllegalStateException(
                    "Unable to open connection with endpoint", ioe);
        }
    }

    private static URL createUrl(final String apiEndpoint) {
        try {
            return new URL(apiEndpoint);
        } catch (MalformedURLException mue) {
            throw new IllegalStateException("Improperly formed URL", mue);
        }
    }

    private static String getResponse(final InputStream inputStream) {
        final StringBuilder response = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    inputStream));
            String output;
            while ((output = bufferedReader.readLine()) != null) {
                response.append(output);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Unable to read from endpoint", ioe);
        }
        return response.toString();
    }

    private static void sendRequest(final String requestBody,
            final OutputStream outputStream) {
        try {
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
        } catch (IOException ioe) {
            throw new IllegalStateException("Unable to write to endpoint", ioe);
        }
    }

    private static void setRequestParamters(
            final HttpURLConnection httpConnection, String apiKey)
            throws ProtocolException {
        httpConnection.setRequestMethod(POST_METHOD);
        httpConnection.setRequestProperty(CONTENT_TYPE_KEY, APPLICATION_JSON);
        httpConnection.setRequestProperty(ACCEPT_HEADER_KEY, APPLICATION_JSON);
        httpConnection.setRequestProperty("api-key", API_KEY);
    }

}
