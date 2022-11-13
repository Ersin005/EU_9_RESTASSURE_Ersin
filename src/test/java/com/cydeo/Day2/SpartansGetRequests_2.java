package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartansGetRequests_2 {


    /*
    1. Given Accept Type application/json
    2. When user send GET request to api/spartans end point
    3. Then status code must be 200
    4. And response content type must be application/json
    5. And response body should include spartan result
     */

    String baseUrl = "http://34.207.128.60:8000";

    @Test
    public void test1() {
        // 2. Create a get request
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");
        // We used given(), accept(), when(), and get() methods. Accept() is like accept in Request Headers at POstman.
        //Then we provide content type inside accept method.

        //3. Then status code must be 200; printing response status through response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //4. And response content type must be application/json; print response content type through response object
        System.out.println("response.contentType() = " + response.contentType());

        //5. And response body should include spartan result; print whole response body
        response.prettyPrint();

        // how to do API Automation Testing?

        //Verify status code is 200
        Assertions.assertEquals(200, response.statusCode() );

        //Verify content type is application/json
        Assertions.assertEquals("application/json", response.contentType() );


    }

    /*
    1. Given Accept header Type application/json
    2. When user send GET request to api/spartans/3 end point
    3. Then status code must be 200
    4. And response content type must be application/json
    5. And response body should contain Fidole
     */

    @DisplayName("GET one Spartan /api/spartans/3 and verify")
    @Test
    public void test2() {
        //1. Given Accept Type application/json
        //    2. When user send GET request to api/spartans/3 end point
        System.out.println("----------------------------------------------------------------------------");
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans/3");


        //3. Then status code must be 200 ==> print
        System.out.println("response.statusCode() = " + response.statusCode());

        // 4. And response content type must be application/json ==>Print
        System.out.println("response.contentType() = " + response.contentType());

        //And response body should contain Fidole
        System.out.println("response.body().asString() = " + response.body().asString());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }

       /*
       1. Given no headers provided
       2. When user send GET request to api/hello
       3. Then response status code must be 200
       4. And response content header type must be "text/plain;charset=UTF-8"
       5. And response header should contain date
       6. And content length should be 17
       7. And response body should be "Hello from Sparta"
     */

    @DisplayName("GET request to api/hello")
    @Test
    public void test3(){
     //1. Given no headers provided
        //       2. When user send GET request to api/hello; send request and store response data to response object
        Response response=RestAssured.given().accept(ContentType.ANY)
                .when().get(baseUrl+"/api/hello");

        // 3. Then response status code must be 200
      Assertions.assertEquals(200, response.statusCode());

     //4. And response content header type must be "text/plain;charset=UTF-8"
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        // 5. And response header should contain date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //6. And content length should be 17
        //How to get header from response by using header key
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        Assertions.assertEquals("17", response.header("Content-Length"));

        // 7. And response body should be "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta", response.body().asString());



    }
}
