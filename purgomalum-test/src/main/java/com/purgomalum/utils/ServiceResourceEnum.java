package com.purgomalum.utils;

public enum ServiceResourceEnum {

    XML("service/xml"),

    JSON("service/json"),

    PLAIN("service/plain"),

    PROFANITY("service/containsprofanity");

    private String url;

    private ServiceResourceEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
