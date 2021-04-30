package com.balsam.oasis.translate.OasisTranslator.utils;

import com.balsam.oasis.translate.OasisTranslator.http.HttpClient;
import com.balsam.oasis.translate.OasisTranslator.http.HttpMethod;
import com.balsam.oasis.translate.OasisTranslator.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ApiService {

    public ApiService() {}

    public String translate(String sourceLang, String targetLang, String text) {
        AtomicReference translatedText = new AtomicReference(text);
        Operations.get()
                .push("CALL-TRANSLATE-API", () -> getTranslatedText(sourceLang, targetLang, text))
                .invoke("CALL-TRANSLATE-API")
                .then(translatedText::set)
                .handleErrorsIfPresent(Exception::printStackTrace);
        return (String) translatedText.get();
    }

    private String getTranslatedText(String sourceLang, String targetLang, String text) {
        StringBuilder response = new StringBuilder();
        try {
            Thread.sleep(500);
            HttpResponse result = new HttpClient().request(HttpMethod.GET, urlHandler(sourceLang, targetLang, encode(decode(text))));
            response.append(result.asString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response.toString();
    }

    private String urlHandler(String sourceLang, String targetLang, String text) throws IllegalAccessException {
        String sourceLanguage = "auto".equals(sourceLang) ? "" : sourceLang;
        return String.format(getAPIUrl(), text, sourceLanguage, targetLang);
    }

    private String getAPIUrl() throws IllegalAccessException {
        GoogleScriptAPIsSingleton instance = GoogleScriptAPIsSingleton.getInstance();
        Field[] declaredFields = GoogleScriptAPIs.class.getDeclaredFields();
        int size = instance.size();

        if (size < declaredFields.length) {
            String api = declaredFields[size].get(new GoogleScriptAPIs()).toString();
            instance.put(size, api);
            return String.format(api, "text=%s&source=%s&targetLangs=%s");
        } else if (size == declaredFields.length) {
            instance.clear();
            String api = declaredFields[0].get(new GoogleScriptAPIs()).toString();
            instance.put(0, api);
            return String.format(api, "text=%s&source=%s&targetLangs=%s");
        }

        return GoogleScriptAPIs.API_1;
    }

    private String decode(String text) throws UnsupportedEncodingException {
        return URLDecoder.decode(text, "UTF-8");
    }

    private String encode(String text) throws UnsupportedEncodingException {
        return URLEncoder.encode(text, "UTF-8");
    }
}
