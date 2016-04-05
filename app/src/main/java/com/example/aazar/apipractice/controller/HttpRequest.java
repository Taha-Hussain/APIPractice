package com.example.aazar.apipractice.controller;

import android.net.Uri;

import com.example.aazar.apipractice.models.RFQ_API_Object;
import com.example.aazar.apipractice.controller.JsonParsing;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Aazar on 17-Jan-16.
 */
public class HttpRequest {

    // parameters/properties
    private String url;
    private String requestMethod;
    private HashMap<String, String> headerProperty;
    private HashMap<String, String> queryProperty;

    // constructors
    public HttpRequest(String url, String requestMethod, HashMap<String, String> headerProperty, HashMap<String, String> queryProperty) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.headerProperty = headerProperty;
        this.queryProperty = queryProperty;
    }

    public HttpRequest(String url, String requestMethod, HashMap<String, String> headerProperty) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.headerProperty = headerProperty;
    }

    public HttpRequest(String url, String requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }
    // constructors end

    // setters and getters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public HashMap<String, String> getHeaderProperty() {
        return headerProperty;
    }

    public void setHeaderProperty(HashMap<String, String> headerProperty) {
        this.headerProperty = headerProperty;
    }

    public HashMap<String, String> getQueryProperty() {
        return queryProperty;
    }

    public void setQueryProperty(HashMap<String, String> queryProperty) {
        this.queryProperty = queryProperty;
    }
    // setters and getters end

    public RFQ_API_Object callAPI() {
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(getRequestMethod());

            // setting the header properties for the URL if any
            if (getHeaderProperty() != null) {
                Map request_header = getHeaderProperty();

                Iterator<Map.Entry<String, String>> iterator = request_header.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> headerObject = iterator.next();
                    conn.setRequestProperty(headerObject.getKey(), headerObject.getValue());

                    // remove the entries if you like
                    iterator.remove();
                }
            }

            // setting the query parameters for the URL if any
            if (getQueryProperty() != null) {
                Map request_header = getQueryProperty();

                Iterator<Map.Entry<String, String>> iterator = request_header.entrySet().iterator();
                Uri.Builder builder = new Uri.Builder();

                while (iterator.hasNext()) {
                    Map.Entry<String, String> queryObject = iterator.next();
                    builder.appendQueryParameter(queryObject.getKey(), queryObject.getValue());

                    // remove the entries if you like
                    iterator.remove();
                }

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
            }

            conn.connect();

            InputStream inputStream = conn.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // Parse the Json data to model
            return JsonParsing.JsonToObject(stringBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
