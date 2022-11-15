package review_Zulpikar.week_1;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.baseURI;

public class PathMethod_6 {
    @BeforeAll
    public static void setUpBase() {
        baseURI = "http://54.167.52.69:8000/api/spartans";
    }

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/10");

//        System.out.println("response.statusCode() = " + response.statusCode());
//        response.prettyPrint();

        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");
        String name = response.path("name").toString();
        String gender = response.path("gender").toString();
        Long phone = response.path("phone");

        Assertions.assertEquals(id,10);
        Assertions.assertEquals(gender,"Female");

    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get();

//        List<Integer> ids = response.path("id");
//        System.out.println(ids);
//
//        List<String> names = response.path("name");
//        System.out.println(names);

        System.out.println(response.path("name[0]").toString());
        System.out.println(response.path("phone[10]").toString());
        System.out.println(response.path("gender[-10]").toString());
        System.out.println(response.path("name[-10]").toString());
    }

    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender","Male")
                .when().get("/search");

        List<String> genders = response.path("content.gender");

        Assertions.assertEquals(genders.get(15),"Male");
        Assertions.assertEquals("53",response.path("totalElement").toString());



    }
}
