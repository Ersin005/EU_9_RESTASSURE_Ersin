package review_Zulpikar.week_1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.baseURI;

import java.util.HashMap;
import java.util.Map;

public class Parameters_5 {


    @BeforeAll
    public static void setUpBase() {
        baseURI = "http://54.167.52.69:8000/api/spartans";
    }

    @Test
    public void pathParams() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 45)
                .when().get("/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
        Assertions.assertTrue(response.body().asString().contains("Heddie"));

    }

    @Test
    public void getSpartanNegativeTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 4500)
                .when().get("/{id}");

        // when negative testing, the status code is 404
        Assertions.assertEquals(404, response.statusCode());

        // validate error message
        Assertions.assertTrue(response.body().asString().contains("Not Found"));
    }

    @Test
    public void queryParam1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "z")
                .when().get("/search");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.body().asString().contains("Lorenza"));
    }

    @Test
    public void queryParams2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("nameContains","c")
                .and().queryParam("gender","Female")
                .when().get("/search");

        Assertions.assertEquals(200,response.statusCode());
    }

    @Test
    public void queryParams3(){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains","c");
        queryMap.put("gender","Female");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/search");

        Assertions.assertEquals(200, response.statusCode());
    }
}
