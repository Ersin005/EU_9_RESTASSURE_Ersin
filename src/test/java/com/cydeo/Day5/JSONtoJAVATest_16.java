package com.cydeo.Day5;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest_16 extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();

        //get the json and convert it to the json

       Map<String,Object> jsonMap= response.as(Map.class);

        System.out.println("jsonMap = " + jsonMap.toString());
        //jsonMap = {id=15, name=Meta, gender=Female, phone=1938695106}

        //We can use hamcrest and junit assertions after we got map
        String actualName= (String) jsonMap.get("name");
        assertThat(actualName, is("Meta"));
        System.out.println("actualName = " + actualName);


    }

    @DisplayName("GET all spartans to Java")
    @Test
    public void getAllSpartans(){


        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .extract().response() ;
        //We need to convert json to java which is deserialize, after this point it's java

        List<Map<String,Object>> jsonList= response.as(List.class);
        //We are trying to convert List *** response.as(List.class); is API part

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));
        //jsonList.get(1).get("name") = Nels
        //The first get() gets the data from map, the second map() gets the data inside the map.


        // Getting one spartan and assigning it to a Map
        Map<String,Object> spartan3=jsonList.get(2);

        System.out.println("spartan3 = " + spartan3);
        //spartan3 = {id=3, name=Fidole, gender=Male, phone=6105035231}

        System.out.println("allSpartans = " + jsonList);

    }






}
