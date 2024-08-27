package api.chaining;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {

    @Test
    void test_createUser(ITestContext context) {

        Faker faker = new Faker();
        String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
        int randomNumb = faker.number().numberBetween(0, 1);
        String sex;
        if (randomNumb == 0) {
            sex = "Male";
        } else {
            sex = "Female";
        }

        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender", sex);
        data.put("email", faker.internet().emailAddress());
        data.put("status", "Inactive");

        int userId = given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .body(data.toString())

                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        System.out.println("Newly created user id is : " + userId);

        context.setAttribute("userId", userId); //Variable context available at test level
        context.getSuite().setAttribute("userId", userId); //variable context available at suite level.
    }

}