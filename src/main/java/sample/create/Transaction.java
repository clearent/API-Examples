package sample.create;

import com.google.gson.Gson;
import sample.domain.transaction.*;
import sample.domain.transaction.Void;

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
    private static final String API_KEY = "YOUR-API-KEY-HERE";
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

    private static String requestSale() throws Exception {
        Sale sale = new Sale();
        Gson gson = new Gson();
        String jsonSale = gson.toJson(sale);
        return requestTransaction(jsonSale);
    }

    public static String requestAuth() throws Exception {
        Auth auth = new Auth();
        Gson gson = new Gson();
        String jsonAuth = gson.toJson(auth);
        return requestTransaction(jsonAuth);
    }

    public static String requestCapture() throws Exception {
        Capture capture = new Capture();
        Gson gson = new Gson();
        String jsonCapture = gson.toJson(capture);
        return requestTransaction(jsonCapture);
    }

    public static String requestForcedSale() throws Exception {
        ForcedSale forced = new ForcedSale();
        Gson gson = new Gson();
        String jsonForced = gson.toJson(forced);
        return requestTransaction(jsonForced);
    }

    public static String requestRefund() throws Exception {
        Refund refund = new Refund();
        Gson gson = new Gson();
        String jsonRefund = gson.toJson(refund);
        return requestTransaction(jsonRefund);
    }

    public static String requestVoid() throws Exception {
        Void voidTransaction = new Void();
        Gson gson = new Gson();
        String jsonVoid = gson.toJson(voidTransaction);
        return requestTransaction(jsonVoid);
    }

    private static String requestTransaction(String requestBody)
            throws IOException {

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = setupHttpConnection(API_URI);
            setRequestParameters(httpConnection, API_URI);
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

    private static void setRequestParameters(
            final HttpURLConnection httpConnection, String apiKey)
            throws ProtocolException {
        httpConnection.setRequestMethod(POST_METHOD);
        httpConnection.setRequestProperty(CONTENT_TYPE_KEY, APPLICATION_JSON);
        httpConnection.setRequestProperty(ACCEPT_HEADER_KEY, APPLICATION_JSON);
        httpConnection.setRequestProperty("api-key", API_KEY);
    }

}
