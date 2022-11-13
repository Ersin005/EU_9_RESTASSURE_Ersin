package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests_3 {

    //@BeforeAll is like @BeforeClass in testNG, we use with static method name

    @BeforeAll
    public static void init(){
        //save baseURI in this variable so that we don't need to type each http method.
        baseURI="http://34.207.128.60:1000/ords/hr";
    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1(){
        Response response=RestAssured.get("/regions");
        //We don't need to provide the baseURl here, because @BeforeAll will do it for us,
        // we only add end point "/regions"

        System.out.println("response.statusCode() = " + response.statusCode());
        //response.statusCode() = 200

    }


    /*
    1. Given Accept Type application/json
    2. When user send GET request to regions/2 end point
    3. Then status code must be 200
    4. And response content type must be application/json
    5. And response body should contain Americas
     */

    @DisplayName("GET request to regions/2")
    @Test
    public void test2(){

        /*
       Response response= RestAssured.given().accept(ContentType.JSON)
               .when()
              .get("regions/2");
        we can delete RestAssured, Response, Assertions etc. if we import all static restassured methods.
        To do so delete Restassured, click on the warning lamp, choose import static methods,
        then select restassured from junit. --->import static io.restassured.RestAssured.*;
        Later replace restassured with * at the import line.
        Do the same thing to Assertions, delete it follow the same steps.
         */

        //1. Given Accept Type application/json
        //            2. When user send GET request to regions/2 end point
        Response response=given().accept(ContentType.JSON)
                .when()
                .get("regions/2");

        //3. Then status code must be 200
        assertEquals(200,response.statusCode());

        // 4. And response content type must be application/json
        assertEquals("application/json", response.contentType());

        response.prettyPrint();

        //5. And response body should contain Americas
        response.body().asString().contains("Americas");




    }

}
