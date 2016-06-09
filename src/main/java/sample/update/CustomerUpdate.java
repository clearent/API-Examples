package sample.update;


import com.google.gson.Gson;
import sample.domain.recurringPayments.CustomerInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CustomerUpdate {

    private static final String API_URI = "https://gateway-qa.clearent.net/rest/v2/customers/CUSTOMER-KEY-HERE";
    private static final String API_KEY = "YOUR-API-KEY-HERE";
    private static final String ACCEPT_HEADER_KEY = "Accept";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String POST_METHOD = "PUT";
    private static final boolean OUTPUT_TRUE = true;

    public static void main(String[] args) throws Exception {
        String response;

        // sale
        System.out.println("Beginning updating Customer");
        response = updateCustomer();
        System.out.println(response);

    }

    private static String updateCustomer() throws Exception {
        CustomerInfo customer = new CustomerInfo();
        Gson gson = new Gson();
        String jsonCustomer = gson.toJson(customer);
        return requestTransaction(jsonCustomer);
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