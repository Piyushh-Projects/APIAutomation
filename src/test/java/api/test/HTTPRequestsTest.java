package api.test;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

    /*
    given()
        Content type, set cookies, add auth, add params, set headers etc...

     when()
        get, post, put, delete

     then()
        validate status code, extract response, extract headers cookies, response body...
     */

public class HTTPRequestsTest {

    int id;

    @Test(priority = 1)
    public void getUser() {

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    public void createUser() {

        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Piyush Agrawal");
        data.put("job", "QA");

        id = given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void updateUser() {

        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Piyushh Bansal");
        data.put("job", "QA Automation");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4, dependsOnMethods = {"createUser"})
    void deleteUser() {

        given()
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(204)
                .log().all();
    }

}
