package APITesting;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _5_SpartanTestsNavigateBodyWithJsonPath {

    @BeforeClass
    public void setUpClass(){
        baseURI = "http://54.144.18.36:8000";
    }

       /*
    Given accept type is Json
    And path parameter id is 11
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content type: application/json
    And response payload values match the following:
        id is 11,
        name is "Nona"
        gender is "Female"
        phone is 7959094216
     */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        //How to read with path() method
        int id = response.body().path("id");
        System.out.println("id = " + id);

        //How to read with JsonPath method
        JsonPath jsonData = response.jsonPath();
        int id1 = jsonData.getInt("id");
        String name = jsonData.getString("name");
        String gender = jsonData.getString("gender");
        long phone = jsonData.getLong("phone");

        assertEquals(id1, 11);
        assertEquals(name, "Nona");
        assertEquals(gender, "Female");
        assertEquals(phone, 7959094216l);


    }


}
