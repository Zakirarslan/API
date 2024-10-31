package post_requests;


import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Post01TR extends PetStoreBaseUrl {
/*
        Given
            https://petstore.swagger.io/v2/pet
        And
            {
                "id": 320,
                "category": {
                    "id": 0,
                    "name": "CAT"
                },
                "name": "MAYA",
                "photoUrls": [
                    "string"
                ],
                "tags": [
                    {
                        "id": 0,
                        "name": "bird"
                    }
                ],
                "status": "available"
            }
        When
            Kullanıcı URL'e bir POST request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
     */
@Test
    public void test01(){

    //1 Set the url

    spec.pathParam("first","pet");

    //set the expected data / payload
    String payload ="{\n" +
            "  \"id\": 14253647,\n" +
            "  \"category\": {\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"Pet\"\n" +
            "  },\n" +
            "  \"name\": \"MuhtarAlabay\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"ali\", \"Veli\",\"Can\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"name\": \"birds\"\n" +
            "    },\n" +
            "     {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"dogs\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";
    //send request get response

    Response response=given(spec)
            .body(payload)
            .when()
            .post("{first}");

    response.prettyPrint();

    response.then()
            .statusCode(200)
            .contentType(ContentType.JSON);







}
}




















