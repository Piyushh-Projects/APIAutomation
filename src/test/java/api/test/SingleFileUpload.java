package api.test;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class SingleFileUpload {

    @Test
    void singleFileUpload() {

        String path = System.getProperty("user.dir") + "//testData//TestFile.txt";
        File myFile = new File(path);

        given()
                .multiPart("file", myFile)
                .contentType("multipart/form-data")

                .when()
                .post("http://localhost:8080/uploadFile")

                .then()
                .statusCode(200)
                .body("fileName", equalTo("TestFile.txt"))
                .log().all();
    }

    @Test
    void multipleFileUpload() {

        String path = System.getProperty("user.dir") + "//testData//TestFile.txt";
        String path1 = System.getProperty("user.dir") + "//testData//TestFile1.txt";
        File myFile = new File(path);
        File myFile1 = new File(path1);


        given()
                .multiPart("files", myFile)
                .multiPart("files", myFile1)
                .contentType("multipart/form-data")

                .when()
                .post("http://localhost:8080/uploadFile")

                .then()
                .statusCode(200)
                .body("[0].fileName", equalTo("TestFile.txt"))
                .body("[1].fileName", equalTo("TestFile1.txt"))
                .log().all();
    }

    @Test
    void multipleFileUpload1() {

        String path = System.getProperty("user.dir") + "//testData//TestFile.txt";
        String path1 = System.getProperty("user.dir") + "//testData//TestFile1.txt";
        File myFile = new File(path);
        File myFile1 = new File(path1);

        File[] fileArr = {myFile, myFile1};

        given()
                .multiPart("files", fileArr)
                .contentType("multipart/form-data")

                .when()
                .post("http://localhost:8080/uploadFile")

                .then()
                .statusCode(200)
                .body("[0].fileName", equalTo("TestFile.txt"))
                .body("[1].fileName", equalTo("TestFile1.txt"))
                .log().all();
    }

    @Test
    void fileDownload() {

        given()

                .when()
                .get("http://localhost:8080/downloadFile/Test1.txt")

                .then()
                .statusCode(200)
                .log().all();

    }

}
