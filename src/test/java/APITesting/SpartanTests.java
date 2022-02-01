package APITesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
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


}
