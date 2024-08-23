package api.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogDemo {

    @Test
    void testLogs() {
        given()

                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().headers()
                .log().cookies()
                .log().body()
                .log().all();
    }
}
