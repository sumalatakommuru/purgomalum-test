package com.purgomalum.steps;

import com.jayway.restassured.response.Response;
import com.purgomalum.service.PurgomalumService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.http.HttpStatus;
import com.jayway.restassured.path.xml.element.Node;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PurgomalumSteps {
    private Response purgomalum;

    @Given("^I request purgomalum service to get (.*) using (.*)$")
    public void iRequestPurgomalumServiceToGetTextUsingContentType(String text, String contentType) throws UnsupportedEncodingException {
        Map<String, String> param = new HashMap<>();
        param.put("text", URLEncoder.encode(text, "UTF-8"));
        purgomalum = PurgomalumService.getText(contentType, param);
    }

    @Then("^I should get success response code$")
    public void iShouldGetSuccessResponseCode() {
        assertThat(purgomalum.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @And("^response should contains (.*) with (.*)$")
    public void responseShouldContainsTextWithContentType(String replaceText, String contentType) {
        if (contentType.equalsIgnoreCase("json")) {
            assertThat(replaceText).as("json format text should be %s", replaceText).isEqualTo(purgomalum.body().path("result"));
        } else if (contentType.equalsIgnoreCase("xml")) {
            assertThat(replaceText).as("xml format text should be %s", replaceText).isEqualTo(purgomalum.xmlPath().get().children().iterator().next());
        } else if (contentType.equalsIgnoreCase("plain")) {
            assertThat(replaceText).as("plain text should be %s", replaceText).isEqualTo(purgomalum.getBody().print());
        }
    }

    @And("^response should return (.*)$")
    public void responseShouldReturnContainsprofanityValue(String value) {
        assertThat(value).as("json format response should be %s", value).isEqualTo(purgomalum.body().print());
    }

    @Given("^I request purgomalum service value to get containsprofanity value (.*) using (.*)$")
    public void iRequestPurgomalumServiceValueToGetContainsprofanityValueTextUsingContentType(String text, String contentType) throws UnsupportedEncodingException {
        Map<String, String> param = new HashMap<>();
        param.put("text", URLEncoder.encode(text, "UTF-8"));
        purgomalum = PurgomalumService.getProfanity(contentType, param);
    }

    @Given("^I request purgomalum service text (.*) to find text (.*) and replace with (.*) using (.*)$")
    public void iRequestPurgomalumServiceTextTextToFindTextFindAndReplaceWithReplaceUsingContentType(String text, String find, String replace, String contentType) throws UnsupportedEncodingException {
        Map<String, String> param = new HashMap<>();
        param.put("text", URLEncoder.encode(text, "UTF-8"));
        param.put("add", URLEncoder.encode(find, "UTF-8"));
        param.put("fill_text", URLEncoder.encode(replace, "UTF-8"));
        purgomalum = PurgomalumService.getText(contentType, param);

    }

    @Given("^I request purgomalum service text (.*) to find text (.*) and fill with (.*) using (.*)$")
    public void iRequestPurgomalumServiceTextTextToFindTextFindAndFillWithFillCharacterUsingContentType(String text, String find, String fill, String contentType) throws UnsupportedEncodingException {
        Map<String, String> param = new HashMap<>();
        param.put("text", URLEncoder.encode(text, "UTF-8"));
        param.put("add", URLEncoder.encode(find, "UTF-8"));
        param.put("fill_char", URLEncoder.encode(fill, "UTF-8"));
        purgomalum = PurgomalumService.getText(contentType, param);
    }

    @Given("^I request purgomalum service text (.*) to replace with (.*) using (.*)$")
    public void iRequestPurgomalumServiceTextTextToReplaceWithReplaceTextUsingContentType(String text, String replaceText, String contentType) throws UnsupportedEncodingException {
        Map<String, String> param = new HashMap<>();
        param.put("text", URLEncoder.encode(text, "UTF-8"));
        param.put("fill_text", URLEncoder.encode(replaceText, "UTF-8"));
        purgomalum = PurgomalumService.getText(contentType, param);
    }
}
