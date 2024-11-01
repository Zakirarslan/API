package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.Map;

import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Get11_ObjectMapper extends JsonPlaceHolderBaseUrl {
/*
Given
         https://jsonplaceholder.typicode.com/todos/198
     When
          I send GET Request to the URL
      Then
          Status code is 200
          And response body is like {
                                     "userId": 10,
                                     "id": 198,
                                     "title": "quis eius est sint explicabo",
                                     "completed": true
                                   }




{"userId": 10,"title": "quis eius est sint explicabo","completed": true}
 */
    @Test
    public void get11(){
        // Set the url
        spec.pathParams("p1","todos","p2","198");

        // set the expected data
        //1.way: method call for map
        Map<String,Object> expectedData1=jsonPlaceHolderMapper(10,"quis eius est sint explicabo",true);
         //2 way:
        JsonPlaceHolderPojo expectedData2=new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);
        // 3way:


    }



}
