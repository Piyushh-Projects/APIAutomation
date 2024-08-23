package api.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParams {

    //https://reqres.in/api/users?page=2&id=5

    @Test
    void testQueryAndPathParams() {

        given()
                .pathParam("pathParam", "users")
                .pathParam("pathParam1", "api")
                .queryParam("page", 2)
                .queryParam("id", 5)

                .when()
                .get("https://reqres.in/{pathParam1}/{pathParam}")
                .then()
                .statusCode(200)
                .log().all();
    }
}