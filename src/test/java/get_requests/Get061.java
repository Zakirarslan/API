package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
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
        given(spec)
                .when()
                .get("{p1}/{p2}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Smith"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"))
                .log().body();









    }

}
