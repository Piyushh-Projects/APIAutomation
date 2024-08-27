package api.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/*
Authentication --> Check user credentials are valid or not.
Authorization --> Check access/permission for valid/authenticated users only.

Types of Authentications supported by restAssured:
1. Basic --> Takes username & password.
2. Digest --> Takes username & password, but internal algorithms are different.
3. Preemptive --> Takes username & password, but internal algorithms are different.
4. Bearer Token --> Token is passed as Header (Bearer + Token).
5. Oauth 1.0 --> Takes 4 parameters consumerKey, consumerSecret, accessToken, secretToken.
6. Oauth 2.0 --> Have to send 2 requests prior to generate OAuth Access Token, it will be sent as Header.
7. API Key --> Takes key, can be passed as Header or Query-Param.
*/

public class Authentications {

    @Test
    void testBasicAuthentication() {

        given()
                .auth().basic("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test
    void testDigestAuthentication() {

        given()
                .auth().digest("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test
    void testPreemptiveAuthentication() {

        given()
                .auth().preemptive().basic("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test
    void testBearerTokenAuthentication() {

        String bearerToken = "ggolvSv4UpUH_a9Qk5x5KAC2YudbptpltVYZ";

        given()
                .headers("Authorization", "Bearer " + bearerToken)

                .when()
                .get("http://gorest.co.in/public-api/users")

                .then()
                .statusCode(200)
                .body("data.message", equalTo("Invalid token"))
                .log().all();
    }

    @Test
    void testOAuth1Authentication() {

        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")

                .when()
                .get("url")

                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void testOAuth2Authentication() {

        String accessToken = "greenAppleRedApple";
        given()
                .auth().oauth2(accessToken)

                .when()
                .get("http://gorest.co.in/public-api/users")

                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void testAPIKeyAuthentication() {

        String key = "fe9c5cddb7e01d747b4611c3fc9eaf2c";

        given()
                .queryParam("appid", key)
                .pathParam("myPath", "data/2.5/forecast/daily")
                .queryParam("q", "kolkata")
                .queryParam("units", "metric")
                .queryParam("cnt", "7")

                .when()
                .get("https://api.openweathermap.org/{myPath}")

                .then()
                .statusCode(200)
                .body("city.name", equalTo("Kolkata"))
                .body("city.country", equalTo("IN"))
                .log().all();
    }

}
