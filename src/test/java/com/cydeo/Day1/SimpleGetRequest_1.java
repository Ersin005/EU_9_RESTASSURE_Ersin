package com.cydeo.Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest_1 {

    String url ="http://34.207.128.60:8000/api/spartans";

    @Test
    public void test1(){

        //1. send a get request and store the response inside the Response object
        Response response = RestAssured.get(url);
        /*
         We used RestAssured.get(); method to call data from that url. Then when we push alt+enter,
         it automatically assigned the data to Response object.
         */

        //2. Check the status code and print.
        System.out.println("response.statusCode() = " + response.statusCode());
        // response.statusCode() = 200


        //3. Print the response body
        response.prettyPrint();


    }
}
