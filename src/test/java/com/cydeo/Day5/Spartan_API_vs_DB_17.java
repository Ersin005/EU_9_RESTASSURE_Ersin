package com.cydeo.Day5;

import com.cydeo.Utilities.DBUtils;
import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Spartan_API_vs_DB_17 extends SpartanTestBase {

    @DisplayName("GET one Spartan from API and DB")
    @Test
    public void testDB1(){

        //get id, name, gender, phone number from database
        // get same info from API
        // compare them

        //GET THE INFO FROM DB
        //1. Create querry as String////
        String querry="select SPARTAN_ID, NAME, GENDER, PHONE from SPARTANS where SPARTAN_ID=15";

        //1.2. Save data inside the Map
        Map<String, Object> dbMap = DBUtils.getRowMap(querry);
        System.out.println("dbMap = " + dbMap);
       // dbMap = {PHONE=1938695106, GENDER=Female, SPARTAN_ID=15, NAME=Meta}
        //>This info comes from DB

        //GET THE INFO FROM API
        //2.
        /*
        ONE WAY OF DESERIALIZATION
       Map<String,Object> apiMap= given().accept(ContentType.JSON)
                .pathParams("id",15)
                .when()
                        .get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().response().as(Map.class);

         */
        Response response = given().accept(ContentType.JSON)
                .pathParams("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().response();

        //deserialization Here json to Java with Jacson ObjectMapper
        Map<String,Object> apiMap=response.as(Map.class);
        System.out.println("apiMap = " + apiMap);
        //apiMap = {id=15, name=Meta, gender=Female, phone=1938695106}

        //3. Compare DB vs API --> We assume that DB is expected
        // Api is always actual, DB is expected
        assertThat(apiMap.get("id").toString(), is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"), is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"), is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(), is(dbMap.get("PHONE").toString()));
        //WE NEED TO USE toString() method for all the numbers.


    }

}
