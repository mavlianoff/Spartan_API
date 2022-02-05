package APITesting;

import static io.restassured.RestAssured.*;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class _9_SpartanPostRequests {

    @BeforeClass
    public void setUpClass(){
        baseURI = "http://54.144.18.36:8000";
    }
    /*
    Given accept type and Content type is JSON
    And request json body is:
        {
          "gender":"Male",
          "name":"Mike",
          "phone":8877445596
        }
    When user sends POST request to "/api/spartans"
    Then the status must be 201
    And content type should be application/json
    and json payload/response should contain:
        "A Spartan is Born" message
    and same data is posted
     */

    @Test
    public void PostWithString(){
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "          \"gender\":\"Male\",\n" +
                        "          \"name\":\"Mike\",\n" +
                        "          \"phone\":8877445596\n" +
                        "        }")
                .when().post("/api/spartans/");

        response.prettyPrint();

        //verify status code and content type
        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(), "application/json");

        //verify success message
        assertEquals(response.path("success"), "A Spartan is Born!");

        //verify post body
        JsonPath json = response.jsonPath();

        assertEquals(json.getString("data.name"), "Mike");
        assertEquals(json.getString("data.gender"), "Male");
        assertEquals(json.getLong("data.phone"), 8877445596l);

    }

    @Test
    public void PostWithMap(){
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "MikeMap");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 8877445596l);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans");

        response.prettyPrint();

        //verification part is same as above test1()


    }

}
