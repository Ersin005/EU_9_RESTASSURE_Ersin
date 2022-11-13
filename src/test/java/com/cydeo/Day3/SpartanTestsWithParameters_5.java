package com.cydeo.Day3;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestsWithParameters_5 {

    //@BeforeAll is like @BeforeClass in testNG, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseURI in this variable so that we don't need to type each http method.
        baseURI = "http://34.207.128.60:8000";

    }

     /*
    1. Given Accept Type application/json
    2. And Id parameter value is 5
    3. When user send GET request to /api/spartans/{id} end point
    4. Then response status code must be 200
    5. And response content type must be application/json
    6. And response body should contain "Bylthe"
     */

    @DisplayName("GET request to /api/spartans/{id} with id 5")
    @Test
    public void test1() {

        //  1. Given Accept Type application/json
        //    2. And Id parameter value is 5
        //    3. When user send GET request to /api/spartans/{id} end point
        Response response = given().accept(ContentType.JSON).
                and().pathParams("id", 5).when().get("/api/spartans/{id}");

        // 4. Then response status code must be 200// verify status code
        assertEquals(200, response.statusCode());

        // 5. And response content type must be application/json//verify content type
        assertEquals("application/json", response.contentType());

        response.prettyPrint();

        // 6. And response body should contain "Bylthe"
        assertTrue(response.body().asString().contains("Blythe"));

    }


    /*
       1. Given Accept Type application/json
       2. And Id parameter value is 500
       3. When user send GET request to /api/spartans/{id} end point
       4. Then response status code must be 404
       5. And response content type must be application/json
       6. And "Not Found" message should be in the response payload

       */

   @DisplayName("GET request /api/spartans/{id} negative test")
    @Test
    public void test2() {
        //1. Given Accept Type application/json
        //       2. And Id parameter value is 500
        //       3. When user send GET request to /api/spartans/{id} end point
        Response response = given().accept(ContentType.JSON).and().pathParams("id", 500)
                .when().get("/api/spartans/{id}");

        //4. Then response status code must be 404
        assertEquals(404, response.statusCode());

        // 5. And response content type must be application/json
        assertEquals("application/json", response.contentType());

        //6. And "Not Found" message should be in the response payload
        assertTrue(response.body().asString().contains("Not Found"));
    }

     /*
       1. Given Accept Type application/json
       2. And Query parameter values are
                            gender|Female
                            nameContains|e
       3. When user send GET request to /api/spartans/search end point
       4. Then response status code must be 200
       5. And response content type must be application/json
       6. And "Female" should be in the response payload
       7. And "Janette" should be in the response payload
       */

    @DisplayName("GET request /api/spartans/search with Query parameters")
    @Test
    public void test3() {
        /*   1. Given Accept Type application/json
             2. And Query parameter values are
                                  gender|Female
                                  nameContains|e
             3. When user send GET request to /api/spartans/search end point
         */

        Response response=given().accept(ContentType.JSON)
                .and().queryParam("nameContains","e")
                .and().queryParam("gender","Female")
                .when().get("/api/spartans/search");

        //4. Then response status code must be 200
        assertEquals(200,response.statusCode());

        //       5. And response content type must be application/json
        assertEquals("application/json",response.contentType());

        //       6. And "Female" should be in the response payload
        assertTrue(response.body().asString().contains("Female"));

        //       7. And "Janette" should be in the response payload
        assertTrue(response.body().asString().contains("Janette"));

    }

    @DisplayName("GET request /api/spartans/search with Query parameters <MAP>")
    @Test
    public void test4() {

        //create a map add querry parameters
        Map<String,Object> querryMap=new HashMap<>();
        querryMap.put("nameContains","e");
        querryMap.put("gender","Female");

        Response response=given().log().all().accept(ContentType.JSON)
                .and().queryParams(querryMap).when().get("/api/spartans/search");

        //4. Then response status code must be 200
        assertEquals(200,response.statusCode());

        //       5. And response content type must be application/json
        assertEquals("application/json",response.contentType());

        //       6. And "Female" should be in the response payload
        assertTrue(response.body().asString().contains("Female"));

        //       7. And "Janette" should be in the response payload
        assertTrue(response.body().asString().contains("Janette"));

        System.out.println("querryMap = " + querryMap);
        //querryMap = {nameContains=e, gender=Female}

    }

}
