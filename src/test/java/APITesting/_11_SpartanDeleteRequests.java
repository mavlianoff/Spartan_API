package APITesting;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class _11_SpartanDeleteRequests {

    @BeforeClass
    public void setUpClass() {
        baseURI = "http://54.144.18.36:8000";
    }

    @Test
    public void test1(){
        //delete request
        given()                             //not sending(ContentType) or expecting(accept) json body
                .pathParam("id", 127)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);       //204 - Success

        //verify spartan is deleted
        given()
                .pathParam("id", 127)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(404);       //404 - deleted, not found anymore

    }



}
