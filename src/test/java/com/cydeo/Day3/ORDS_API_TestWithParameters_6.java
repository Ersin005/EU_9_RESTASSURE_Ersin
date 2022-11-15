package com.cydeo.Day3;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDS_API_TestWithParameters_6 extends HRTestBase {



     /*
    1. Given Accept Type application/json
    2. And parameters q={"region_id":2}
    3. When user send GET request to /countries end point
    4. Then response status code must be 200
    5. And response content type must be application/json
    6. And response body should contain "United States of America"
      */


    @DisplayName("GET request to /countries with Querry Param")
    @Test
    public void test1() {
        //1. Given Accept Type application/json
        //    2. And parameters q={"region_id":2}
        //    3. When user send GET request to /countries end point

        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .and().get("/countries");


        // 4. Then response status code must be 200
        assertEquals(200, response.statusCode());


        //    5. And response content type must be application/json
        assertEquals("application/json", response.header("Content-Type"));
        //We can use header() method instead of contentType() method. We can use header for all the headers, not only contentType.

        //    6. And response body should contain "United States of America"
        assertTrue(response.body().asString().contains("United States of America"));

    }

    /*
    Send a GET request to employees and get only the employees who work as an IT-PROG.
     */

    @DisplayName("GET request to /employees with Querry Param")
    @Test
    public void test2(){

        Response response= given().log().all().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .and().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();
    }

}
