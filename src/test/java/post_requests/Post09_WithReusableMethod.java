package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Post09_WithReusableMethod extends JsonPlaceHolderBaseUrl {
    /*Given
         1) https://jsonplaceholder.typicode.com/todos
                2) {
            "userId": 55,
                    "title": "Tidy your room",
                    "completed": false
        }
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
    public void post09() throws JsonProcessingException {
       /*
        Given
        1) https://jsonplaceholder.typicode.com/todos
        2) {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
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



        //Set the Url
        spec.pathParam("p1", "todos");

        //Set the expected data
        //Use Pojo class Object to create the expected data
        JsonPlaceHolderPojo payload = new JsonPlaceHolderPojo(55,"Tidy your room",false);

        //Send the request and get the response
        Response response = given(spec).when().body(payload).post("{p1}"); //Serialization
        response.prettyPrint();

        //Do assertion
        //De-serialization
        //1st way: asString();

        //2nd way: JsonPath

        //3rd way: HashMap

        //4th way: POJO

        //5th way: Object Mapper with POJO
        JsonPlaceHolderPojo actualData = convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(201, response.statusCode());
        assertEquals(payload.getUserId(), actualData.getUserId());
        assertEquals(payload.getTitle(), actualData.getTitle());
        assertEquals(payload.getCompleted(), actualData.getCompleted());


    }
}



