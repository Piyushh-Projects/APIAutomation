package api.endpoints;

//Created to perform Create, Read, Update, Delete requests

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPointsFromProperties {

    //Method to read URL from properties
    static ResourceBundle getURL() {
        //Load properties file
        return ResourceBundle.getBundle("routes");
    }

    public static Response createUser(User payload) {
        String post_url = getURL().getString("post_url");
        Response response;
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);

        return response;
    }

    public static Response readUser(int userId) {
        String get_url = getURL().getString("get_url");
        Response response;
        response = given()
                .pathParam("userId", userId)
                .when()
                .get(get_url);

        return response;
    }

    public static Response updateUser(int userId, User payload) {
        String update_url = getURL().getString("update_url");
        Response response;
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userId", userId)
                .body(payload)
                .when()
                .put(update_url);

        return response;
    }

    public static Response deleteUser(int userId) {
        String delete_url = getURL().getString("delete_url");
        Response response;
        response = given()
                .pathParam("userId", userId)
                .when()
                .delete(delete_url);

        return response;
    }
}