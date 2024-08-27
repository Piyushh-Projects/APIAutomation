package api.test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingXMLResponse {

    @Test
    void testXMLResponse() {

        given()

                .when()
                .get("https://mocktarget.apigee.net/xml")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8")
                .body("root.firstName", equalTo("John"))
                .body("root.lastName", equalTo("Doe"))
                .log().all();
    }

    @Test
    void testXMLResponse1() {

        Response response = given()
                .when()
                .get("https://mocktarget.apigee.net/xml");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/xml; charset=utf-8");

        String firstname = response.xmlPath().get("root.firstName").toString();
        String lastname = response.xmlPath().get("root.lastName").toString();

        Assert.assertEquals(firstname, "John");
        Assert.assertEquals(lastname, "Doe");

        System.out.println(response.getBody().toString());
    }

    @Test
    void testXMLResponse2() {

        Response response = given()
                .when()
                .get("https://mocktarget.apigee.net/xml");


        XmlPath xmlpath = new XmlPath(response.asString());
        List<String> users = xmlpath.getList("root.firstName");
        int usersSize = users.size();
        Assert.assertEquals(usersSize, 1);
        Assert.assertTrue(users.contains("John"));
        for (String user : users) {
            System.out.println(user);
        }
    }

}
