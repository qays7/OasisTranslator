package com.balsam.oasis.translate.OasisTranslator.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestFactory {

    public HttpRequest getRequest(HttpMethod method, String url,
                    HttpParameterSet parameters) throws IOException {
        HttpRequest request;

        if (method == HttpMethod.GET) {
            request = requestGET(url);
        } else {
            request = requestPOST(url, parameters);
        }

        return request;
    }

    private HttpRequest requestGET(String url)throws IOException {
        StringBuilder request = new StringBuilder(url);
        HttpURLConnection httpConnection = getDefaultConnection(request.toString());
        httpConnection.setRequestMethod(HttpMethod.GET.asString());
        return new HttpRequest(httpConnection);
    }

    private HttpRequest requestPOST(String url, HttpParameterSet parameters)
                    throws IOException {
        String params = new HttpParameterSetParser(parameters).asString();

        HttpURLConnection httpConnection = getDefaultConnection(url);
        httpConnection.setRequestMethod(HttpMethod.POST.asString());
        httpConnection.setRequestProperty("Content-Length",
                        String.valueOf(params.getBytes()));

        httpConnection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(
                        httpConnection.getOutputStream());

        writer.write(params);
        writer.flush();
        return new HttpRequest(httpConnection);
    }

    private HttpURLConnection getDefaultConnection(String request)throws IOException {
        URL urlRequest = new URL(request);
        HttpURLConnection httpConnection = (HttpURLConnection) urlRequest.openConnection();
        httpConnection.setRequestProperty("User-Agent", HttpClient.USER_AGENT);
        return httpConnection;
    }

}
