package api.chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Test
    void test_deleteUser(ITestContext context) {

        String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
        //int userId = (Integer) context.getAttribute("userId"); // Comes from createUser request
        int userId = (Integer) context.getSuite().getAttribute("userId");

        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParam("userId", userId)

                .when()
                .delete("https://gorest.co.in/public/v2/users/{userId}")

                .then()
                .statusCode(204)
                .log().all();
    }

}