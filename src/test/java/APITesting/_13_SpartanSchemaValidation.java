package APITesting;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import java.util.List;
import java.util.Map;

public class _13_SpartanSchemaValidation {

    //Purpose is to make sure entered data is validated with requirements and descriptions
    //1. submit json body to get json schema. JSON Schema tool: https://www.jsonschema.net/home
    //2. select complete schema and test with input body. JSON Schema validator: https://www.jsonschema.net/home
    //3. Update POM with validator schema(io.restassured):JSON Schema Validator
    //4. Create directory/resources under 'test', aligh with 'java': resources
    //5. Create file: SingleSpartanSchema.json
    //6. Send validation request via IntelliJ

    @BeforeClass
    public void setUpClass(){
        baseURI = "http://54.144.18.36:8000";
    }

    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }



}
