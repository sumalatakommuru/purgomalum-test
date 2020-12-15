package com.purgomalum.service;

import com.jayway.restassured.response.Response;
import com.purgomalum.utils.ServiceResourceEnum;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import static com.purgomalum.utils.APIHelper.givenConfig;


public class PurgomalumService {

    public static Response getText(String contentType, Map<String, String> param) throws UnsupportedEncodingException {
        return givenConfig(contentType)
                .queryParameters(param)
                .log().all().get(getServiceResourceUrl(contentType));
    }

    public static Response getProfanity(String contentType, Map<String, String> param) {
        String url = ServiceResourceEnum.PROFANITY.getUrl();
        return givenConfig(contentType)
                .queryParameters(param)
                .log().all().get(url);
    }

    public static Response getReplaceText(String contentType, Map<String, String> queryParam) {
        return givenConfig(contentType).
                queryParameters(queryParam).
                log().all().
                get(getServiceResourceUrl(contentType));
    }



    private static String getServiceResourceUrl(String contentType) {
        String url = null;
        if (contentType.equalsIgnoreCase("json")) {
            url = ServiceResourceEnum.JSON.getUrl();
        } else if (contentType.equalsIgnoreCase("xml")) {
            url = ServiceResourceEnum.XML.getUrl();
        } else if (contentType.equalsIgnoreCase("plain")) {
            url = ServiceResourceEnum.PLAIN.getUrl();
        }
        return url;
    }
}

