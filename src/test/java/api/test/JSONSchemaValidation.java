package api.test;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

//https://jsonformatter.org/json-to-jsonschema

import static io.restassured.RestAssured.given;

public class JSONSchemaValidation {

    @Test
    void jsonSchemaValidation() {

        given()

                .when()
                .get("https://gorest.co.in/public/v2/users")

                .then()
                .assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath("userjsonSchema.json"))
                .log().all();

    }

}
