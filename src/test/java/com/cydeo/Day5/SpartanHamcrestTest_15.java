package com.cydeo.Day5;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanHamcrestTest_15 extends SpartanTestBase {

    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test1() {
        //along with this statement I want to save names inside the List<String>
       List<String> names= given()
                .accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "j",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                //We can use multiple parameters in querry, as a map, key ad value format, here select
                // .queryParams then provide parameters like "gender" as key ,"Male" as value
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", greaterThanOrEqualTo("3"))
                .extract().response().jsonPath().getList("content.name");

        System.out.println("names = " + names);


    }
    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test2() {
        //get status
        int statusCode= given()
                .accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "j",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                //We can use multiple parameters in querry, as a map, key ad value format, here select
                // .queryParams then provide parameters like "gender" as key ,"Male" as value
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", greaterThanOrEqualTo("3"))
                .extract().response().statusCode();
        //or .extract().statusCode();

        System.out.println("statusCode = " + statusCode);


    }

}
