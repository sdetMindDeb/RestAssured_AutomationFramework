package Testcases;

import com.github.javafaker.Faker;
import endpoints.userEndPoints_PropertiesFile;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.User;

public class userTest_propertyFile {

    Faker faker;
    User userPayload;

    public Logger logger;

    @BeforeClass
    public void setupData(){
        faker=new Faker();
        userPayload=new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger= (Logger) LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser(){
        logger.info("******Creating Users*******");
        Response response=userEndPoints_PropertiesFile.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
        logger.info("******Users created*******");
    }

    @Test(priority = 2)
    void testGetUserByName(){
        logger.info("******Reading User info*******");
        Response response=userEndPoints_PropertiesFile.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("******User info displayed*******");
    }

    @Test(priority = 3)
    void testUpdateUserByName(){
        logger.info("******Updating user*******");
        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());


        Response response=userEndPoints_PropertiesFile.updateUser(userPayload,this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //checking data after update
        Response responseAfterUpdate=userEndPoints_PropertiesFile.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
        logger.info("******User updated*******");
    }

    @Test(priority = 4)
    void testDeleteUserByName(){
        logger.info("******Deleting user*******");
        Response response=userEndPoints_PropertiesFile.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //checking data after update
        Response responseAfterUpdate=userEndPoints_PropertiesFile.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
        logger.info("******Deleted User*******");
    }


}
