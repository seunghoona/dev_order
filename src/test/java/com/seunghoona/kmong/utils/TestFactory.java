package com.seunghoona.kmong.utils;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class TestFactory {

    public static ExtractableResponse<Response> get(String url) {
        return givenLog()
                .get(url)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> post(String url, Object obj) {
        return givenLog()
                .when()
                .body(obj)
                .post(url)
                .then().log().all()
                .extract();
    }

    private static RequestSpecification givenLog() {
        return given().log().all().contentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
