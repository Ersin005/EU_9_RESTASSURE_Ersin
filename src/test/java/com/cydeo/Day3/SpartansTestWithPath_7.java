package com.cydeo.Day3;

import com.cydeo.Utilities.SpartanTestBase;
import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartansTestWithPath_7 extends SpartanTestBase {



    /*
    1. Given Accept Type application/json
    2. And path param id is 10
    3. When user sends a GET request to /api/spartans/{id} end point
    4. Then response status code must be 200
    5. And response content type must be application/json
    6. And response payload values match the following:
            id:10
            name: "Lorenza"
            gender:Female
            phone:3312820936
      */
    @DisplayName("Get one spartan with PATH METHOD")
    @Test
    public void test1() {

        //1. Given Accept Type application/json
        //    2. And path param id is 10
        //    3. When user sends a GET request to /api/spartans/{id} end point
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");


        //4. Then response status code must be 200
        assertEquals(200, response.statusCode());

        //5. And response content type must be application/json
        assertEquals("application/json", response.contentType());


        //6. And response payload values match the following:
        //            id:10
        //            name: "Lorenza"
        //            gender:Female
        //            phone:3312820936

        /*
        Verify each json key which has specific value
         */

        System.out.println("response.path(\"id\") = " + response.path("id").toString());
        System.out.println("response.path(\"name\") = " + response.path("name").toString());
        System.out.println("response.path(\"gender\") = " + response.path("gender").toString());
        System.out.println("response.path(\"phone\") = " + response.path("phone").toString());


        //return type of response.path() can be casted to any type automatically.
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        //assert the values
        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);


    }

    @DisplayName("Get all spartan and navigate with PATH METHOD")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");

        //response.prettyPrint();

       int firstId= response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstName=response.path("name[0]");
        System.out.println("firstName = " + firstName);

        String lastFirstNameInTheList=response.path("name[-1]");
        System.out.println("lastFirstNameInTheList = " + lastFirstNameInTheList);

        System.out.println("--------------------------------- Save names inside the list of string -------------------------------------------");
        //Save names inside the list of string
        List<String> names=response.path("name");
        System.out.println("names = " + names);

        System.out.println("--------------------------------- print each name one by one -------------------------------------------");
        //print each name one by one
        for (String eachName : names) {
            System.out.println(eachName);

        }


    }
}
