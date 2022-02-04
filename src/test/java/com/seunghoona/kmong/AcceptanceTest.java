package com.seunghoona.kmong;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.snippet.Snippet;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private DatabaseCleanUp databaseCleanUp;

    @RegisterExtension
    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension(
            "build/generated-snippets");

    protected static RequestSpecification spec;


    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
            databaseCleanUp.afterPropertiesSet();
        }
        spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .build();
        databaseCleanUp.execute();
    }

    public static ExtractableResponse<Response> getAuth(String url, String token) {
        return givenLog()
                .auth().oauth2(token)
                .get(url)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> getAuthDocs(String url, String token) {
        return getAuthDocs(url, url, token);
    }

    public static ExtractableResponse<Response> getAuthDocs(String url, String docs, String token) {
        return givenLogDocs(docs)
                .auth().oauth2(token)
                .get(url)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> postAuthDocs(String url, Object obj, String token) {
        return postAuthDocs(url, url, obj, token);
    }

    public static ExtractableResponse<Response> postAuthDocs(String url, String docs, Object obj, String token) {
        return givenLogDocs(docs)
                .auth().oauth2(token)
                .when()
                .body(obj)
                .post(url)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> postAuth(String url, Object obj, String token) {
        return givenLog()
                .auth().oauth2(token)
                .when()
                .body(obj)
                .post(url)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> postDocs(String url, Object obj) {
        return givenLogDocs(url)
                .when()
                .body(obj)
                .post(url)
                .then().log().all()
                .extract();
    }

    private static RequestSpecification givenLog() {
        return given(spec).log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    private static RequestSpecification givenLogDocs(String name) {
        return given(spec).log().all()
                .filter(document(name,
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                )).contentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
