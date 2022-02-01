package APITesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTests {

    String spartanBaseURL = "http://54.144.18.36:8000";

    @Test
    public void viewSpartanTest1(){

        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");

        //print status code
        System.out.println(response.statusCode());

        //print body
        System.out.println(response.body().prettyPrint());
        System.out.println(response.body().asString());


    }
    /*
    When user send GET request to /api/spartans end point
    Then status code must be 200
    And body should contain Allen
     */
    @Test
    public void viewSpartanTest2(){
        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");
        //verify status code
        Assert.assertEquals(response.statusCode(), 200);
        //verify it contains Allen
        Assert.assertTrue(response.body().asString().contains("Allen"));

    }


}
