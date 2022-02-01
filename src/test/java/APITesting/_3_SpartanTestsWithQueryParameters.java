package APITesting;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class _3_SpartanTestsWithQueryParameters {

    @BeforeClass
    public void setUpClass(){
        baseURI = "http://54.144.18.36:8000";
    }

    /*
    Given accept type is Json
    And query parameter values are:
    gender/Female
    nameContains/J
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content type: application/json
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */

    @Test
    public void QueryParam1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J")
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();
    }

    @Test
    public void queryParam2Map(){

        //creating map for query parameters
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "Female");
        paramsMap.put("nameContains", "J");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Janette"));


    }


}

