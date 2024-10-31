package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post07_ObjectMapperMap extends JsonPlaceHolderBaseUrl {
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
    public void post07() throws JsonProcessingException {
        //Set the url
        spec.pathParam("p1","todos");

        //Set the expected data
        Map<String,Object> payload=jsonPlaceHolderMapper(55,"Tidy your room",false);

        //Send the request and get the post response
        Response response=given(spec).when().body(payload).post("{p1}");
        response.prettyPrint();

        //Do assertions
        // Do de-serialization
        //1.way: asString
        System.out.println("response.asString() = " + response.asString());

        //2.way: JsonPath
        JsonPath jsonPath=response.jsonPath();
        System.out.println("jsonPath.getString(\"title\") = " + jsonPath.getString("title"));

        //3.way

        Map<String,Object> actualData1=response.as(HashMap.class);

        //4.way :POJO
        JsonPlaceHolderPojo actualdata=response.as(JsonPlaceHolderPojo.class);

        //5.way: Object Mapper with MAP
       // ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> actualData=new ObjectMapper().readValue(response.asString(), HashMap.class);

        assertEquals(201,response.statusCode());
        assertEquals(payload.get("userId"),actualData.get("userId"));
        assertEquals(payload.get("title"),actualData.get("title"));
        assertEquals(payload.get("completed"),actualData.get("completed"));




    }

}





























