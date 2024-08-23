package api.test;

import static io.restassured.RestAssured.*;

import api.payload.POJO;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;

/*
Different ways to create POST request body:
1. Using Hashmap
2. Using Org.JSON
3. Using POJO
4. Using Json file
*/

public class WaysToCreateRequestBody {

    @Test(priority = 1)
    void testPostUsingHashMap() {

        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();

        map2.put("year", "2021");
        map2.put("price", "1000");
        map2.put("CPU model", "m2");
        map2.put("Hard disk size", "256 GB");

        map1.put("name", "Apple MacBook Air");
        map1.put("data", map2);

        given()
                .contentType("application/json")
                .body(map1)
                .when()
                .post("https://api.restful-api.dev/objects")
                .then()
                .statusCode(200)
                .body("name", equalTo("Apple MacBook Air"))
                .header("Content-Type", "application/json")
                .log().all();
    }

    @Test(priority = 2)
    void testPostUsingJsonLibrary() {

        HashMap<String, String> map = new HashMap<>();
        JSONObject data = new JSONObject();

        map.put("year", "2021");
        map.put("price", "1000");
        map.put("CPU model", "m2");
        map.put("Hard disk size", "256 GB");

        data.put("name", "Apple MacBook Air");
        data.put("data", map);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://api.restful-api.dev/objects")
                .then()
                .statusCode(200)
                .body("name", equalTo("Apple MacBook Air"))
                .header("Content-Type", "application/json")
                .log().all();
    }

    @Test(priority = 3)
    void testPostUsingPOJO() {

        POJO pojo = new POJO();
        HashMap<String, String> map = new HashMap<>();

        map.put("year", "2021");
        map.put("price", "1000");
        map.put("CPU model", "m2");
        map.put("Hard disk size", "256 GB");

        pojo.setName("Apple MacBook Air");
        pojo.setData(map);

        given()
                .contentType("application/json")
                .body(pojo)
                .when()
                .post("https://api.restful-api.dev/objects")
                .then()
                .statusCode(200)
                .body("name", equalTo("Apple MacBook Air"))
                .header("Content-Type", "application/json")
                .log().all();
    }

    @Test(priority = 4)
    void testPostUsingJsonFile() throws FileNotFoundException {

        File file = new File(System.getProperty("user.dir") + "//testData//body.json");
        FileReader reader = new FileReader(file);
        JSONTokener jt = new JSONTokener(reader);
        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://api.restful-api.dev/objects")
                .then()
                .statusCode(200)
                .body("name", equalTo("Apple MacBook Air 14"))
                .header("Content-Type", "application/json")
                .log().all();
    }

}
