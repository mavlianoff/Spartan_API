package APITesting;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class _10_SpartanPutPatchRequests {

    @BeforeClass
    public void setUpClass() {
        baseURI = "http://54.144.18.36:8000";
    }

    @Test
    public void PutRequest(){
        //3 different ways to send json body
        //1 - String
        //2 - Java collection (Map)
        //3- POJO

        //using map
        Map<String, Object> putMap = new HashMap<>();
        putMap.put("name", "MikePUT");
        putMap.put("gender", "Male");
        putMap.put("phone", 1234567890l);

        //we are gonna send a request body with updated value, and content type header
        given()              //NO accept(ContentType.JSON) => we dont get response body in return
                .contentType(ContentType.JSON)  //sending JSON body
                .and().pathParam("id", 101)
                .and().body(putMap)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);



    }

    @Test
    public void PatchRequest(){
        //using map
        Map<String, Object> patchMap = new HashMap<>();
        patchMap.put("name", "MikePATCH");

        //we are gonna send a request body with updated value, and content type header
        given()              //NO accept(ContentType.JSON) => we dont get response body in return
                .contentType(ContentType.JSON)  //sending JSON body
                .and().pathParam("id", 101)
                .and().body(patchMap)
                .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);



    }



}
