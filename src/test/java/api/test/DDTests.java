package api.test;

import api.endpoints.UserEndPoints;
import api.utilities.DataProviders;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.payload.User;

public class DDTests {

    User userPayload;
    private static int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        userId = userID;
    }


    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String name, String job) {
        userPayload = new User();
        userPayload.setName(name);
        userPayload.setJob(job);

        Response response = UserEndPoints.createUser(userPayload);
        int id = Integer.parseInt(response.path("id"));
        setUserId(id);
        System.out.println("User with id " + id + " has been created!");
        Assert.assertEquals(response.getStatusCode(), 201);

    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userId) {
        Response response = UserEndPoints.deleteUser(Integer.parseInt(userId));
        System.out.println("User with id " + userId + " has been deleted!");
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
