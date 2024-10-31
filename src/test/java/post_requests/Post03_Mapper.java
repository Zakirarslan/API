package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Hashtable;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post03_Mapper extends JsonPlaceHolderBaseUrl {
    /*
     Given
     1) https://jsonplaceholder.typicode.com/todos
     2)  {
           "userId": 55,
           "title": "Tidy your room",
           "completed": false
        }
     3) Content type should be json
  When
      I send POST Request to the Url
  Then
      Status code is 201
  And
      response body is like
                   {
                      "userId": 55,
                      "title": "Tidy your room",
                      "completed": false,
                      "id": 201
                    }
NOTE: for payload {"userId": 55, "title": "Tidy your room", "completed": false}
     */
    @Test
    public void post03() {  // JsonPath for assertion

        //Set the URL
        spec.pathParam("first", "todos");

        // Set the expected data ( payload => the data that you want to transfer to the server)
          Map<String, Object> payload = jsonPlaceHolderMapper(55,"Tidy your room",false);

        //Send request and get response
        Response response = given(spec).when()
                .body(payload).post("{first}");
        response.prettyPrint();

        // Do assertions

        Map<String, Object> actualData=response.as(Hashtable.class);  // De-serialization

        assertEquals(201,response.statusCode());
        assertEquals(payload.get("userId"),actualData.get("userId"));
        assertEquals(payload.get("title"),actualData.get("title"));
        assertEquals(payload.get("completed"),actualData.get("completed"));

        //We didn't use any hard code in this test method






    }
}
































