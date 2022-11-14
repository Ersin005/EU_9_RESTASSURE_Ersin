package review_Zulpikar.week_1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.baseURI;

public class PathCydeoStudent_7 {
    @BeforeAll
    public static void setUpBase() {
        baseURI = "https://api.training.cydeo.com";
    }

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/student/15");

        response.prettyPrint();

        String id = response.path("students.studentId").toString();

        Assertions.assertEquals("[15]",id);
        Assertions.assertEquals("[Leopold]",response.path("students.firstName").toString());
    }
}
