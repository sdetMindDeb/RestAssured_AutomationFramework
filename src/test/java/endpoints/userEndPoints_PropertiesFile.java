package endpoints;

//Created to perform CRUD operations


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class userEndPoints_PropertiesFile {

    //method created to get URL from properties file
    static ResourceBundle getUrl(){
        ResourceBundle routes=ResourceBundle.getBundle("routes");//load property files
        return routes;
    }

    public static Response createUser(User payload){

        String post_url=getUrl().getString("post_url");

        Response res=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return res;
    }

    public static Response readUser(String userName){
        String get_url=getUrl().getString("get_url");

        Response res=given()
                .pathParam("username",userName)
                .when()
                .get(get_url);
        return res;
    }

    public static Response updateUser(User payload,String userName){

        String update_url=getUrl().getString("update_url");

        Response res=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(update_url);
        return res;
    }

    public static Response deleteUser(String userName){

        String delete_url=getUrl().getString("delete_url");

        Response res=given()
                .pathParam("username",userName)
                .when()
                .delete(delete_url);
        return res;
    }
}
