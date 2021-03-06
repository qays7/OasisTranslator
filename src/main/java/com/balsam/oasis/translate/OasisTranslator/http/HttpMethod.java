package com.balsam.oasis.translate.OasisTranslator.http;

public enum HttpMethod {

    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");

    private String value;

    HttpMethod(String value) {
        this.value = value;
    }

    public String asString() {
        return this.value;
    }

}
