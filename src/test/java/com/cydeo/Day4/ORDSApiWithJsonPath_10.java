package com.cydeo.Day4;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RedirectSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath_10 extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1(){
        Response response=get("/countries");

        //get the second county name

         //to use jsonPath we assign response to jsonPath

        JsonPath jsonPath=response.jsonPath();

       String secondCountryName= jsonPath.getString("items[1].country_name");

        System.out.println("countryName = " + secondCountryName);
        //countryName = Australia

        //get all the county ids

       List<String>  allCountrIds= jsonPath.getList("items.country_id");
        System.out.println("allCountrIds = " + allCountrIds);
        //allCountrIds = [AR, AU, BE, BR, CA, CH, CN, DE, DK, EG, FR, IL, IN, IT, JP, KW, ML, MX, NG, NL, SG, UK, US, ZM, ZW]

        //Get all the countries where the region id is 2
        List<String> allCountryNamesWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("allCountryNamesWithRegionId2 = " + allCountryNamesWithRegionId2);

    }

    




}
