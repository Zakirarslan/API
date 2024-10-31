package post_requests;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Post02TR extends JsonPlaceHolderBaseUrl{
    /*
Given
   1) https://jsonplaceholder.typicode.com/todos
   2)  {
         "userId": 55,
         "title": "Tidy your room",
         "completed": false
       }
    When
        Kullanıcı URL'e bir POST request gönderir
    Then
        Status code 201 olmalı
    And
        Response şu şekilde olmalı:
        {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
        }
 */

    @Test
    public void test01(){
        // set url
        spec.pathParam("first","todos");

        //set the expected data / payload
        String payload="{\n" +
                "    \"userId\": 55,\n" +
                "    \"title\": \"Tidy your room\",\n" +
                "    \"completed\": false,\n" +
                "    \"id\": 201\n" +
                "}";

        // send request get response

        Response response=given(spec).body(payload)
                .when()
                .post("{first}");
        response.prettyPrint();
        //do assertion

        //1.yol jsonpath ile assertion
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(55,jsonPath.getInt("userId"));
        Assert.assertEquals("Tidy your room",jsonPath.getString("title"));
        Assert.assertFalse(jsonPath.getBoolean("completed"));
        Assert.assertEquals(201,jsonPath.getInt("id"));


        // 2. yol
        response
                .then()
                .body("userId",equalTo(55))
                .body("title",equalTo("Tidy your room"))
                .body("completed" , equalTo(false))
                .body("id", equalTo(201))
                .log().body();



    }
}














