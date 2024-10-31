package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Post05_Pojo extends JsonPlaceHolderBaseUrl {
   /* Given
    https://jsonplaceholder.typicode.com/todos
    {
        "userId": 55,
            "title": "Tidy your room",
            "completed": false
    }
    When
    I send POST Request to the Url
            Then
    Status code is 201
    And
    response body is like {
        "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
    }
    */
    @Test
    public void post05(){
        //Set the url
        spec.pathParam("p1","todos");

        //Set the expected data
        JsonPlaceHolderPojo payload=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("payload = " + payload);

        // Send the request and get the response
        Response response=given(spec).when().body(payload).post("{p1}");
        response.prettyPrint();

        //Do assertion
        JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(payload.getUserId(),actualData.getUserId());
        assertEquals(payload.getTitle(),actualData.getTitle());
        assertEquals(payload.getCompleted(),actualData.getCompleted());





    }

}


















