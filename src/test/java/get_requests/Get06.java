package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class Get06 extends HerokuAppBaseUrl {

    /*
     Given
            https://restful-booker.herokuapp.com/booking/10
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
         {
            "firstname": "Sally",
            "lastname": "Ericsson",
            "totalprice": 554,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-07-21",
                "checkout": "2023-02-04"
            },
            "additionalneeds": "Breakfast"
        }
     */

    @Test
    public void get06(){
        // Set the URL
        spec.pathParams("p1","booking", "p2", "130");

        //Send the request and get the response
        Response response = RestAssured.given(spec).get("{p1}/{p2}");         // syntax for more than one path param => "{}/{}"
        response.prettyPrint();

        // Assertion
        // 1st way: using matchers (equalTo() or is() method)
            // with equalTo()
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", CoreMatchers.equalTo("John"),
                        "lastname", CoreMatchers.equalTo("Smith"),
                        "totalprice", CoreMatchers.equalTo(111),
                        "depositpaid", CoreMatchers.equalTo(true),
                        "bookingdates.checkin", CoreMatchers.equalTo("2018-01-01"),
                        "bookingdates.checkout", CoreMatchers.equalTo("2019-01-01"),
                        "additionalneeds", CoreMatchers.equalTo("Breakfast"));


            //is() method
            response.then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("firstname", CoreMatchers.is("John"),
                            "lastname", CoreMatchers.is("Smith"),
                            "totalprice", CoreMatchers.is(111),
                            "depositpaid", CoreMatchers.is(true),
                            "bookingdates.checkin", CoreMatchers.is("2018-01-01"),
                            "bookingdates.checkout", CoreMatchers.is("2019-01-01"),
                            "additionalneeds", CoreMatchers.is("Breakfast"));

            /*
            NOTE: We have multiple ways of extracting the data out of the response body;
                i) asString() method => changes data type of response to String
                ii) JsonPath
                iii) Maps
                iv) Pojo Class
             */




    }



}
