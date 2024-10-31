package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get061 extends HerokuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/3729
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type should contain "application/json"
    And
        Response body should be like;
            {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
 */
    @Test
    public void test061(){
        // Set the URL
        spec.pathParams("p1","booking","p2","130");

        // 2 - Set the expected dat
        // 3 - send request get response
        // 4 - do assertion
        RestAssured.given(spec)
                .when()
                .get("{p1}/{p2}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", CoreMatchers.equalTo("John"))
                .body("lastname", CoreMatchers.equalTo("Smith"))
                .body("totalprice", CoreMatchers.equalTo(111))
                .body("depositpaid", CoreMatchers.equalTo(true))
                .body("bookingdates.checkin", CoreMatchers.equalTo("2018-01-01"))
                .body("bookingdates.checkout", CoreMatchers.equalTo("2019-01-01"))
                .body("additionalneeds", CoreMatchers.equalTo("Breakfast"))
                .log().body();









    }

}
