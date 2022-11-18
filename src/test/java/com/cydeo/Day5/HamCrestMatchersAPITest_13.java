package com.cydeo.Day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HamCrestMatchersAPITest_13 {

    @DisplayName("One Spartan with Hamcrest and chaining")
    @Test
    public void test1() {
/*
    1. Given accept Type application/json
    2. And path param id is 15
    3. When user sends a GET request to spartans/{id} end point
    4. Then response status code must be 200
    5. And response content type must be application/json
    6. And response payload values match the following:
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106
      */

        given().log().all()
                //Informations coming from log().all()
                //Request method:	GET
                //Request URI:	http://34.207.128.60:8000/api/spartans/15
                //Proxy:			<none>
                //Request params:	<none>
                //Query params:	<none>
                //Form params:	<none>
                //Path params:	id=15
                //Headers:		Accept=application/json, application/javascript, text/javascript, text/json
                //Cookies:		<none>
                //Multiparts:		<none>
                //Body:			<none>
        .accept(ContentType.JSON)
                .and().pathParams("id", 15)
                .when().get("http://34.207.128.60:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .and()
                .body("id", equalTo(15),
                        "name", equalTo("Meta"),
                        "gender", is("Female"),
                        "phone", equalTo(1938695106))
                .log().all();
        //Informations of response part coming from log().all()
        // HTTP/1.1 200
        //Content-Type: application/json
        //Transfer-Encoding: chunked
        //Date: Thu, 17 Nov 2022 16:16:55 GMT
        //Keep-Alive: timeout=60
        //Connection: keep-alive
    }

    @DisplayName("CBTraining \"Teacher Request\" with chaining and matchers")
    @Test
    public void teacherData() {

        given().accept(ContentType.JSON)
                .and()
                .pathParams("id", 5)
                .when()
                .get("https://api.training.cydeo.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("server", "envoy")
                // .and()
                //   .header("Content-Lenght" , "9")
                //the headers return type is string, so we used "9"
                .and()
                .header("date", notNullValue())
                .and()
                .and().assertThat()
                .body("teachers[0].firstName", equalTo("Mario"))
                .body("teachers[0].lastName", is("Luigi"))
                .body("teachers[0].gender", is(equalTo("Male")));
    }

    @DisplayName("Get request to teacher/all and chaining")
    @Test
    public void teachersTest() {
        //verify Ron, Tory, Yet, Porter first names are in all teachers

        given().accept(ContentType.JSON)
                .when()
                .get("https://api.training.cydeo.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .body("teachers.firstName", hasItems("Ron", "Tory", "Yet", "Porter"));
        //if we don't provide index number, it will get all the first names into a List.

    }




}
