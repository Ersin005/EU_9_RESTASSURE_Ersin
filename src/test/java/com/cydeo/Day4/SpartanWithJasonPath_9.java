package com.cydeo.Day4;

import com.cydeo.Utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJasonPath_9 extends SpartanTestBase {
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
    @DisplayName("Get one Spartan with JsonPath")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        String name = response.path("name").toString();
        System.out.println("name = " + name);

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name1 = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone= jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


    }
}
