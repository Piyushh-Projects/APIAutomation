package api.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJSONResponse {

    @Test
    void testJsonResponse() {

        given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .statusCode(200)
                .body("data[2].email", equalTo("tobias.funke@reqres.in"));

    }

    @Test
    void testJsonResponse1() {

        Response response = given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users?page=2");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");
        String response_email = response.jsonPath().get("data[2].email").toString();
        Assert.assertEquals(response_email, "tobias.funke@reqres.in");
    }

    @Test
    void testJsonResponse2() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2");

        //Convert response to Json object type
        JSONObject json = new JSONObject(response.asString());

        boolean status = false;
        for (int i = 0; i < json.getJSONArray("data").length(); i++) {
            String data_email = json.getJSONArray("data").getJSONObject(i).get("email").toString();
            System.out.println("email id is : " + data_email);
            if (data_email.equals("tobias.funke@reqres.in")) {
                status = true;
                break;
            }
        }
        Assert.assertTrue(status);

        int total = 0;
        for (int i = 0; i < json.getJSONArray("data").length(); i++) {
            String data_id = json.getJSONArray("data").getJSONObject(i).get("id").toString();
            System.out.println("id is : " + data_id);
            total += Integer.parseInt(data_id);
        }
        System.out.println("Sum of id is : " + total);
        Assert.assertEquals(total, 57);
    }
}
