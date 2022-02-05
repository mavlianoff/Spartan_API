package APITesting;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class _7_SpartanTestsNavigateWithJsonToCollection {

    //converting json response to Java collection for further navigation: de-serialization
    //json parser (mapper) used here is GSON, library added to pom dependencies

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

        //convert json response to java collection
        Map <String, Object>spartanMap = response.body().as(Map.class);

        System.out.println("spartanMap.get(\"id\") = " + spartanMap.get("id"));
        System.out.println("spartanMap.get(\"name\") = " + spartanMap.get("name"));

        //one example verification one side map/ expected value
        assertEquals(spartanMap.get("name"), "Nona");

    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //response.prettyPrint();

        //convert full list spartans to list of maps
        List<Map<String, Object>> listOfSpartans = response.body().as(List.class);

        //explore all data of first spartan
        Map<String, Object> firstSpartan = listOfSpartans.get(0);
        System.out.println("firstSpartan.get(\"name\") = " + firstSpartan.get("name"));

        int counter=1;
        for (Map<String, Object> eachSpartanMap : listOfSpartans) {
            System.out.println(counter + " - spartan = " + eachSpartanMap);
            counter++;
        }


    }









}
