package APITesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _2_SpartanTestsWithPathParameters {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI = "http://54.144.18.36:8000";
    }

    /*
    Given accept type is Json
    And id parameter value is 18
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content type: application/json
    And "Allen" should be in response payload
     */
    @Test
    public void PathTest1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                                        .pathParam("id", 18)
                                        .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);                   //Assert library is static now
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Allen"));

        response.body().prettyPrint();
    }



}
