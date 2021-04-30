package com.balsam.oasis.translate.OasisTranslator.http;

public class HttpParameter {

    private String name;
    private String value;

    public HttpParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

}
