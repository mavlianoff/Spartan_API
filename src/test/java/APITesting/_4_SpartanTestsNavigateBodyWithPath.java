package APITesting;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class _4_SpartanTestsNavigateBodyWithPath {

    @BeforeClass
    public void setUpClass(){
        baseURI = "http://54.144.18.36:8000";
    }

    /*
    Given accept type is Json
    And path parameter id is 10
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content type: application/json
    And response payload values match the following:
        id is 10,
        name is "Lorenzo"
        gender is "Female"
        phone is 3312820936
     */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        int id = response.body().path("id");
        String name = response.body().path("name");
        String gender = response.body().path("gender");
        long phone = response.body().path("phone");

        assertEquals(id, 10);
        assertEquals(name, "Lorenza");
        assertEquals(gender, "Female");
        assertEquals(phone, 3312820936l);


    }

    @Test
    public void test2(){
        Response response = get("/api/spartans");       //extracting all spartans

        //extract first spartan id
        int firstID = response.body().path("id[0]");
        System.out.println("firstID = " + firstID);

        //extract first spartan name
        String firstName = response.body().path("name[0]");
        System.out.println("firstName = " + firstName);

        //extract last spartan name
        String lastName = response.body().path("name[-1]");
        System.out.println("lastName = " + lastName);

        //extract all spartan names
        List<String> spartanNames = response.body().path("name");
        System.out.println("spartanNames = " + spartanNames);
        System.out.println("spartanNames.size() = " + spartanNames.size());

        List<Object> phoneNumbers = response.path("phone");
        for (Object phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }

    }



}
