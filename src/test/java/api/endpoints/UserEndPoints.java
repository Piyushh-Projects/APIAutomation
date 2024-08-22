package api.endpoints;

//Created to perform Create, Read, Update, Delete requests

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payload.User;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User payload) {
        Response response;
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

        return response;
    }

    public static Response readUser(int userId) {
        Response response;
        response = given()
                .pathParam("userId", userId)
                .when()
                .get(Routes.get_url);

        return response;
    }

    public static Response updateUser(int userId, User payload) {
        Response response;
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userId", userId)
                .body(payload)
                .when()
                .put(Routes.update_url);

        return response;
    }

    public static Response deleteUser(int userId) {
        Response response;
        response = given()
                .pathParam("userId", userId)
                .when()
                .delete(Routes.delete_url);

        return response;
    }
}