package com.cydeo.Utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {
    @BeforeAll
    public static void init() {

        baseURI = "http://34.207.128.60:8000";

    }
}
