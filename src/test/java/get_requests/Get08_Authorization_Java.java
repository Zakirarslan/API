package get_requests;

import base_urls.ContactListBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get08_Authorization_Java extends ContactListBaseUrl {
    /*
   Given
       https://thinking-tester-contact-list.herokuapp.com/contacts
   When
       Kullanıcı URL'e bir GET request gönderir
   Then
       HTTP Status Code 200 olmalı
   And
       Content Type "application/json" olmalı
*/
    @Test
    public void test01(){
        //1 - Set tehe Url
        spec.pathParam("first", "contacts");

        // 2. Set the expected data
        // 3. Send the request and get the response

        RestAssured.given(spec)
                .when()
                .get("{first}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all();




    }
}
