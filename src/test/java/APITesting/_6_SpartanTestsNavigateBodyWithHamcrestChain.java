package APITesting;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _6_SpartanTestsNavigateBodyWithHamcrestChain {

    @BeforeClass
    public void setUpClass() {
        baseURI = "http://54.144.18.36:8000";
    }
        /*
    Given accept type is Json
    And path parameter id is 15
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content type: application/json
    And response payload values match the following:
        id is 15,
        name is "Meta"
        gender is "Female"
        phone is 1938695106
     */

    @Test
    public void test1(){
                //request
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                //response
                .then().statusCode(200).and().assertThat().contentType("application/json");
    }

    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().body("id", Matchers.equalTo(15),
                        "name", Matchers.equalTo("Meta"),
                        "gender", Matchers.equalTo("Female"),
                        "phone", Matchers.equalTo(1938695106));
            //Matchers class can be imported static
    }






}
