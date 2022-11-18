package com.cydeo.Day4;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class ORDSApiWithJsonPath_10 extends HRTestBase {
    @DisplayName("GET request to Countries")
    @Test
    public void test1() {
        Response response = get("/countries");

        //get the second county name

        //to use jsonPath we assign response to jsonPath

        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");

        System.out.println("countryName = " + secondCountryName);
        //countryName = Australia

        //get all the county ids

        List<String> allCountrIds = jsonPath.getList("items.country_id");
        System.out.println("allCountrIds = " + allCountrIds);
        //allCountrIds = [AR, AU, BE, BR, CA, CH, CN, DE, DK, EG, FR, IL, IN, IT, JP, KW, ML, MX, NG, NL, SG, UK, US, ZM, ZW]

        //Get all the countries where the region id is 2
        List<String> allCountryNamesWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("allCountryNamesWithRegionId2 = " + allCountryNamesWithRegionId2);

    }

    @DisplayName("Get request to /employees with query parameters")
    @Test
    public void test2() {
        //We added queryParam to have all the employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        //get me all the emails of employees who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();
        List<String> allEmailsOfIT_PROGs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");

        System.out.println("allEmailsOfIT_PROGs = " + allEmailsOfIT_PROGs);
        //allEmailsOfIT_PROGs = [AHUNOLD, BERNST, DAUSTIN, VPATABAL, DLORENTZ]

        //get me first_name of employees who gets more than 10.000
        List<String> emplooyeesGettingMoreThan_10K = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("emplooyeesGettingMoreThan_10K = " + emplooyeesGettingMoreThan_10K);
        //emplooyeesGettingMoreThan_10K = [Steven, Neena, Lex, Nancy, Den, John, Karen, Alberto, Gerald, Eleni, Clara, Lisa, Ellen, Michael, Shelley]


        //get the max salary first name
        String maxSalaryPeople=jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("maxSalaryPeople = " + maxSalaryPeople);
        //maxSalaryPeople = Steven


        //The same structure works for response path
        String maxSalaryKing=response.path("items.max {it.salary}.first_name");
        System.out.println("maxSalaryKing = " + maxSalaryKing);
        //maxSalaryKing = Steven
    }

}
