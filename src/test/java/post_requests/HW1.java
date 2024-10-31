package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Hw1JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class HW1 extends JsonPlaceHolderBaseUrl {

    /*
 Given
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
    public void hw1(){
        // set the url
        spec.pathParam("p1","todos");

        // Set the expacted data
        Hw1JsonPlaceHolderPojo payload=new Hw1JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("payload = " + payload);

        //  //Send the request and get the response

        Response response=given(spec).when().body(payload).post("{p1}");
        response.prettyPrint();

        Hw1JsonPlaceHolderPojo actualData=response.as(Hw1JsonPlaceHolderPojo.class);

        // Du assertions

        assertEquals(201,response.statusCode());
        assertEquals(payload.getUserId(),actualData.getUserId());
        assertEquals(payload.getTitle(),payload.getTitle());
        assertEquals(payload.getCompleted(),actualData.getCompleted());

    }
}




















