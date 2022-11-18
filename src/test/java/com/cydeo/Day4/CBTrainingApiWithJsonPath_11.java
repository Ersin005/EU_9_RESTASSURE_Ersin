package com.cydeo.Day4;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingApiWithJsonPath_11 {
    @BeforeAll
    public static void setup() {
        //save baseUrl inside this variable so that we don't need to type each http method
        String baseURI = "https://api.training.cydeo.com";
    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1() {
        //send a get request to student id 23401 as a path parameter and accept header application/headers
        //verify status code=200 / content type=application/json;charset=UTF-8 / Content-Encoding = gzip
        //verify date header exists
        //assert that
        /*
        firstName Vera
        batch 14
        section 12
        email address aaa@gmail.com
        companyName Cydeo
        stale IL
        zipCode 60606

        using jsonPath
         */

        Response response= given().accept(ContentType.JSON)
                .and().pathParam("id",23401)
                .when().get(baseURI+"/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

    }

}
