package com.cydeo.Day2;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest_4 {

    @BeforeAll
    public static void init(){
        //save baseURI in this variable so that we don't need to type each http method.
        baseURI="http://34.207.128.60:8000";
    }


    /*
   1. Given Accept Type application/xml
   2. When user send GET request to api/spartans/{id} end point
   3. Then status code must be 406
   4. And response content type must be application/xml
    */

    @DisplayName("GET request to api/spartans/10 negative test")
    @Test
    public void test1(){
        // 1. Given Accept Type application/xml
        //   2. When user send GET request to api/spartans end point
        Response response=given().accept(ContentType.XML).when().get("/api/spartans/10");

       //assertEquals(200,(given().accept(ContentType.XML).when().get("/api/spartans/10").statusCode()));
        //3. Then status code must be 406
       assertEquals(406,response.statusCode());

        // 4. And response content type must be application/xml
        assertEquals("application/xml;charset=UTF-8", response.contentType());


    }
}
