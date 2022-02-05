package APITesting;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class _12_Authorization_BookIt {

    //How to get access token: GET {{BASEURL}}/api/sign and provide email and password in parameters and SEND

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

    @BeforeClass
    public void setUpClass() {
        baseURI = "https://cybertek-reservation-api-qa3.herokuapp.com";
    }

    @Test
    public void test1(){
        Response response = given().header("Authorization", accessToken)
                .when().get("/api/campuses");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
    }


}
