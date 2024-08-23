package api.test;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class CookiesDemo {

    @Test
    void testCookies() {

        given()

                .when()
                .get("https://www.google.com")

                .then()
                .cookie("AEC", "AVYB7cp29iM2Q51BoV09cR3RVUEjSCKtXDUYm1VYE9hfe8theXxqejnvHyM")
                .log().all();
    }

    @Test
    void testCookiesInfo() {
        Response response = given()
                .when()
                .get("https://www.google.com");

        //Get specific cookie information
        String cookie_value = response.getCookie("AEC");
        System.out.println("The value of cookie is : " + cookie_value);

        //Get all cookies information
        Map<String, String> all_cookies_value = response.getCookies();

        for (String cookie : all_cookies_value.keySet()) {
            String cookieValue = response.getCookie(cookie);
            System.out.println(cookie + " " + cookieValue);

        }
    }
}
