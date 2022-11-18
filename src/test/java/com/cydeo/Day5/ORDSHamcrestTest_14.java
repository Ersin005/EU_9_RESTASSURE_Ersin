package com.cydeo.Day5;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ORDSHamcrestTest_14 extends HRTestBase {

    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest() {
        //send a get request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)
        //expected names
        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                //if we use hasItems, it means it will check if the table has any IT_PROG, but not check all of them. To check only the values having
                // IT_PROG in each job_id, we need iteration, and everyItem() method gives me this iteration. It gets only the job_ids having IT_PROGs.
                //It's exactly same as looping, and after this method we can use everyItem(equalTo(), startsWith(), endsWith(), contains()), etc.
                // .body("items.first_name", containsInRelativeOrder("De Haan", "Bruce", "David", "Nancy", "Jose Manuel"))
                //in "containsInRelativeOrder()" method, we need to enter the values in order.
                // the values turn as a list, so we can assign them to a list and use in these methods
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana")) //contains with order
                .body("items.email", containsInAnyOrder("VPATABAL", "DAUSTIN", "BERNST", "AHUNOLD", "DLORENTZ")) //contains without order
                .body("items.first_name", equalTo(names)); // equality of lists assertion
//left side od semi-column is a list, and right side of it is also a list, we compare them. we can also use equalTo() method.
//to get the names directly
        // we have 2 methods; containsInAnyOrder()===>  can be in any order and containsInRelativeOrder()
    }


    //  @DisplayName("")
    @Test
    public void employeesTest1() {

        //We want to get response object and chaining at the same time

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().response();
        // .extract() method allows us return to response() or jsonPath();

        response.prettyPrint();
        /*
       int statusCode = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"items\":\"IT_PROG\"")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().response().statusCode();

                we can assign it to int status code and we can change return type to statusCode by using .extract().response().statusCode();

                OR we can use .extract().jsonPath() to change the return type to jsonPath and we can assign it to jsonPath

         */

    }

    @Test
    public void employeesTest2() {
        //we want to chain and also get response object
        JsonPath jsonPath = given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();
//extract() --> method that allow us to get response object after we use then() method.
        //assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.first_name"), hasSize(5));
        //assert firstnames order
        assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));
    }




}
