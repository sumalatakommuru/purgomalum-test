package com.purgomalum.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.SSLConfig;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.RedirectConfig.redirectConfig;
import static com.purgomalum.utils.LoadProps.getproperty;

public class APIHelper {

    static {
        RestAssured.baseURI = getproperty("baseUri").toString();
    }

    public static RequestSpecification givenConfig(String contentType) {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.config = config().redirect(redirectConfig().followRedirects(true));
        RestAssured.config = config().sslConfig(new SSLConfig().allowAllHostnames());

        RequestSpecification requestSpecification = given().baseUri(RestAssured.baseURI)
                .relaxedHTTPSValidation()
                .urlEncodingEnabled(false)
                .header("Accept-Language", "en");
        if (contentType.equalsIgnoreCase("json")) {
            requestSpecification.header("Content-Type", "application/json");
        } else if (contentType.equalsIgnoreCase("xml")) {
            requestSpecification.header("Content-Type", "application/xml");
        } else if (contentType.equalsIgnoreCase("plain")) {
            requestSpecification.header("Content-Type", "text/plain");
        } else {
            requestSpecification.header("Content-Type", "application/json");
        }

        return requestSpecification;
    }

}
