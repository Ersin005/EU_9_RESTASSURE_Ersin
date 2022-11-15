package com.review_With_Zulpikar.week_1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

public class SpartanTest_4 {

    String baseUrl = "http://54.167.52.69:8000/api/spartans";

    @Test
    public void bodyValidation(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl);

        System.out.println(response.body().asString().contains("Nels"));

    }

    @Test
    public void getHeaders(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl);

        System.out.println(response.getHeaders());
    }

    @Test
    public void getHeardersList(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl);

        List<Headers> headers = Collections.singletonList(response.headers());

        System.out.println(headers.toString());

//        System.out.println(headers.get(0).toString());
    }

    @Test
    public void hasHeader(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl);

        System.out.println(response.headers().hasHeaderWithName("Content-Type"));
        System.out.println(response.headers().hasHeaderWithName("Connection"));
    }

    @Test
    public void headerValue(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl);

        System.out.println(response.header("Date"));
        System.out.println(response.header("Transfer-Encoding"));
        System.out.println(response.header("Content-Type"));
    }
}
