package com.cydeo.Day4;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath_8 extends HRTestBase {
    @DisplayName("GET request to countries with PATH method")
    @Test
    public void test1(){

        Response response=given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200,response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //has more
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first country id
        String firstCountryId=response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName=response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print href of Canada
        String hrefOfCanada= response.path("items[2].links[0].href");
        System.out.println("hrefOfCanada = " + hrefOfCanada);
        //hrefOfCanada = http://34.207.128.60:1000/ords/hr/countries/CA

        //Get me all the countries ===> returns a list of countries as a List, they are in an array...
        List<String> countries=response.path("items.country_name");
        System.out.println("countries = " + countries);
        //countries = [Argentina, Brazil, Canada, Mexico, United States of America]

        //assert that all region_ids equal to 2
        List<Integer>allRegion_ids=response.path("items.region_id");
        System.out.println("allRegion_ids = " + allRegion_ids);
        //allRegion_ids = [2, 2, 2, 2, 2]

        for (Integer eachRegion_id : allRegion_ids) {
            System.out.println("eachRegion_id = " + eachRegion_id);
            assertEquals(2,eachRegion_id);
            //eachRegion_id = 2
            //eachRegion_id = 2
            //eachRegion_id = 2
            //eachRegion_id = 2
            //eachRegion_id = 2

        }

    }
    @DisplayName("GET request to /employees with Querry Param")
    @Test
    public void test2(){

        Response response= given().log().all().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .and().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("IT_PROG"));

        // make sure we have only IT-PROG as a job_id.

        List<String> allJobIds=response.path("items.job_id");
        System.out.println("allJobIds = " + allJobIds);
        //allJobIds = [IT_PROG, IT_PROG, IT_PROG, IT_PROG, IT_PROG]
        //OR;

        for (String eachJobId : allJobIds) {
            System.out.println("eachJobId = " + eachJobId);
            //eachJobId = IT_PROG
            //eachJobId = IT_PROG
            //eachJobId = IT_PROG
            //eachJobId = IT_PROG
            //eachJobId = IT_PROG
            assertEquals("IT_PROG",eachJobId);
        }

        //print each name of IT_PROGs
        List<String> names=response.path("items.first_name");

        for (String eachName : names) {
            System.out.println(eachName);
            //Alexander
            //Bruce
            //David
            //Valli
            //Diana

        }


    }
}
