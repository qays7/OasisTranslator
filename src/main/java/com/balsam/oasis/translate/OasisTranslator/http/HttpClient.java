package com.balsam.oasis.translate.OasisTranslator.http;

import java.io.IOException;
import java.net.URL;

public class HttpClient {

    public static String USER_AGENT = "Mozilla/5.0";
    public static String CHARSET = "UTF-8";

    public HttpResponse request(HttpMethod method, URL url) throws IOException {
        return request(method, url.toString(), null);
    }

    public HttpResponse request(HttpMethod method, String url) throws IOException {
        return request(method, url, null);
    }

    public HttpResponse request(HttpMethod method, String url,
        HttpParameterSet httpParameters) throws IOException {
        HttpRequest request = new HttpRequestFactory().getRequest(method, url, httpParameters);

        return request.doRequest();
    }

}
