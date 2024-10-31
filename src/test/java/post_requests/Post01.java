package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.testng.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {

    /*Given
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
            "id": 201*/

    @Test
    public void post01(){
        // Set the Url
        spec.pathParam("first","todos");

        // Set expected data (payload => the data that you want to transfer to server)
        String payload="{\n" +
                "        \"userId\": 55,\n" +
                "                \"title\": \"Tidy your room\",\n" +
                "                \"completed\": false\n" +
                "    }";

        // Send request

        Response response=given(spec)
                .when()
                .body(payload)
                .when()
                .post("{first}");
        response.prettyPrint();

        // Do assertion

        response.then()
                .contentType(ContentType.JSON)
                .statusCode(201);

        response.then()
                .body("userId",is(55))
                .body("title",is("Tidy your room"))
                .body("completed",is(false))
                .body("id",equalTo(201));

        //2.way

        JsonPath jsonPath=response.jsonPath();
        // Check the status code
        assertEquals(201, response.statusCode());

        // Check the response body values
        assertEquals(55, jsonPath.getInt("userId"));
        assertEquals("Tidy your room", jsonPath.getString("title"));
        assertEquals(false, jsonPath.getBoolean("completed"));

    }
}



















