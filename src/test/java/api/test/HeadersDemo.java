package api.test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class HeadersDemo {

    @Test
    void testHeaders() {

        given()

                .when()
                .get("https://www.google.com")

                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server", "gws")
                .and()
                .log().all();
    }

    @Test
    void testHeadersInfo() {

        Response response = given()

                .when()
                .get("https://www.google.com");

        String header_value = response.getHeader("Content-Type");
        System.out.println("The value of Content-Type header is : " + header_value);

        Headers allHeaders = response.getHeaders();
        for (Header hd : allHeaders) {
            System.out.println(hd.getName() + " " + hd.getValue());
        }
    }
}
