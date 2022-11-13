package com.cydeo.Day4;

import com.cydeo.Utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath_8 extends HRTestBase {

    @DisplayName("GET request to countries with PATH method")
    @Test
    public void test1(){

        Response response=given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2")
                .and().get("/countries");

    }
}
