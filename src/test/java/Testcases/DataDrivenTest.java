package Testcases;

import endpoints.userEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import payload.User;

public class DataDrivenTest {

    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProvider.class)
    public void testPostUser(String userId,String userName,String FirstName,String LastName,String Email,String password,String phone){

        User userPayload=new User();

        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(FirstName);
        userPayload.setLastName(LastName);
        userPayload.setEmail(Email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response= userEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 2,dataProvider = "getUserNames",dataProviderClass = DataProvider.class)
    public void testDeleteUserByName(String userName){
        Response response=userEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
