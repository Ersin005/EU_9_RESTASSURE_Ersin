package review_Zulpikar.week_1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class CydeoGetRequest_3 {
    String baseUrl = "https://api.training.cydeo.com";

    @Test
    public void getAllStudents(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl + "/student/all");

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();
    }

    @Test
    public void getByStudentId(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl + "/student/14");

        System.out.println("response.statusCode() = " + response.statusCode());
    }

    @Test
    public void getAllTearchers(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl + "/teacher/all");

        System.out.println("response.statusCode() = " + response.statusCode());
    }
}
