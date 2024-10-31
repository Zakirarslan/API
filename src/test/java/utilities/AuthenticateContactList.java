package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthenticateContactList {

    //BU CLASSIN AMACI DİNAMİK BİR ŞEKİLDE TOKEN ALMAK (REUSABLE METHOD İLE)

    public static String generateToken() {
        //set the url
        //bu endpointi bir token oluşturmak için api dokumanından aldık

        String url="https://thinking-tester-contact-list.herokuapp.com/users/login";

        //set the expected data / payload

        String payload="{\n" +
                "    \"email\": \"zakirarslan@hotmail.com\",\n" +
                "    \"password\": \"zakirjan\"\n" +
                "}";
        //send request get response

        Response response=given()
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .post(url);
         return response.jsonPath().getString("token");
    }


}



