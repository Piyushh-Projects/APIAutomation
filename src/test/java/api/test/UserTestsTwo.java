package api.test;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPointsFromProperties;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTestsTwo {

    Faker faker;
    User userPayload;
    public Logger logger;
    private static int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        userId = userID;
    }

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setName(faker.name().fullName());
        userPayload.setJob(faker.job().title());

        //Logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("******* Creating user *******");
        Response response = UserEndPointsFromProperties.createUser(userPayload);
        response.then().log().all();
        int id = Integer.parseInt(response.path("id"));
        setUserId(id);
        System.out.println("User " + id + " has been created!");
        Assert.assertEquals(response.getStatusCode(), 201);
        logger.info("******* User is created *******");
    }

    @Test(priority = 2)
    public void testGetUserById() {
        logger.info("******* Reading user information *******");
        Response response = UserEndPointsFromProperties.readUser(2);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******* User info is displayed *******");
    }

    @Test(priority = 3)
    public void testUpdateUserById() {
        logger.info("******* Updating user information*******");
        userPayload.setName(faker.name().fullName());
        userPayload.setJob(faker.job().title());

        Response response = UserEndPointsFromProperties.updateUser(getUserId(), userPayload);
        response.then().log().all();
        response.then().log().body().statusCode(200);
        System.out.println("User " + getUserId() + " has been updated!");
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******* User is updated *******");
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        logger.info("******* Deleting user *******");
        Response response = UserEndPointsFromProperties.deleteUser(getUserId());
        System.out.println("User " + getUserId() + " has been deleted!");
        Assert.assertEquals(response.getStatusCode(), 204);
        logger.info("******* User deleted *******");
    }
}
