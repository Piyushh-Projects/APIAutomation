package api.chaining;

import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUser {

    @Test
    void test_getUser(ITestContext context) {

        String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
        //int userId = (Integer) context.getAttribute("userId");  //Comes from create user request
        int userId = (Integer) context.getSuite().getAttribute("userId");

        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParam("userId", userId)
                .contentType(ContentType.JSON)

                .when()
                .get("https://gorest.co.in/public/v2/users/{userId}")

                .then()
                .statusCode(200)
                .log().all();
    }
}