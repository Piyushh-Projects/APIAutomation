package api.test;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class XMLSchemaValidation {

    @Test
    void xmlSchemaValidation() {

        given()

                .when()
                .get("https://mocktarget.apigee.net/xml")

                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("userXmlSchema.xsd"))
                .log().all();

    }
}
