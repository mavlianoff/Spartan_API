package APITesting;

import static io.restassured.RestAssured.*;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class _8_SpartanTestsNavigateBodyWithJsonToPOJO {

    //POJO - Plain Old Java Object created via custom [Spartan] class with private variables + getter/setter
    //GSON library used for json parser (object mapper)

    @BeforeClass
    public void setUpClass(){
        baseURI = "http://54.144.18.36:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        //how to convert json response to spartan POJO class
        Spartan spartan1 = response.body().as(Spartan.class);

        //verify each key with spartan object
        assertEquals(spartan1.getId(), 15);
        assertEquals(spartan1.getName(), "Meta");
        assertEquals(spartan1.getGender(), "Female");
        assertEquals(spartan1.getPhone(),1938695106);

    }

    @Test
    public void gsonExample(){
        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "    \"id\": 11,\n" +
                "    \"name\": \"Nona\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 7959094216\n" +
                "}";

        //De-serializa: JSON --> Java Object
        Spartan spartanNona = gson.fromJson(myJsonBody, Spartan.class);
        System.out.println(spartanNona.toString());

        //Serialize: Java Object --> JSON
        Spartan spartanMike = new Spartan(101, "Mike", "Male", 32566345663l);
        String jsonBody = gson.toJson(spartanMike);
        System.out.println(jsonBody.toString());
    }





}
