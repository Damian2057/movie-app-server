package com.mobile.server.api.httpconnection;

import com.mobile.server.exception.types.ApiExceptions;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class MovieApiConnection implements Closeable {

    private HttpURLConnection connection;
    private String url = "";
    private String method;
    private String appendEndPoint = "";
    private String appendParam;

    public MovieApiConnection(String url, String key) {
        this.url = url.replace("\"", "");
        key = key.replace("\"", "");
        appendParam = "?api_key=" + key;
    }

    public void setRequestMethod(String method) {
        this.method = method;
    }

    public void appendEndPoint(String appendEndPoint) {
        this.appendEndPoint += appendEndPoint;
    }

    public void appendParam(String appendParam) {
        this.appendParam += "&" + appendParam;
    }

    public void buildRequest() {
        url += appendEndPoint + appendParam;
    }

    public String response() throws IOException {
        log.debug("attempting to connect to film API");
        URL object = new URL(url);
        connection = (HttpURLConnection) object.openConnection();
        connection.setRequestMethod(method);
        checkConnectionCode();
        String inputLine;
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (IOException e) {
            throw new ApiExceptions.ConnectionException("Error reading data from Api");
        }

        return content.toString();
    }

    private void checkConnectionCode() throws ApiExceptions.ConnectionException {
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new ApiExceptions.ConnectionException("Connection error! Code: " + responseCode +
                        " " + connection.getResponseMessage());
            } else {
                log.info("GET Response Code :: " + responseCode);
            }
        } catch (IOException | ApiExceptions.ConnectionException e) {
            throw new ApiExceptions.ConnectionException("Connection error!");
        }
    }

    @Override
    public void close() throws IOException {
        connection.disconnect();
    }

}