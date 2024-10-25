package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get07 extends HerokuAppBaseUrl {
    // JSON Path
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
    public void jsonPath(){
        // Set the URL
        spec.pathParams("p1","booking", "p2", "130");

        //Send the request and get the response
        Response response = given(spec).get("{p1}/{p2}");         // syntax for more than one path param => "{}/{}"
        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();
        String firstName=jsonPath.getString("firstname");
        System.out.println("firstName = " + firstName);
        System.out.println("jsonPath.getString(\"totalprice\") = " + jsonPath.getInt("totalprice"));
        System.out.println("jsonPath.getString(\"depositpaid\") = " + jsonPath.getBoolean("depositpaid"));
        System.out.println("jsonPath.getString(\"bookingdates\") = " + jsonPath.getString("bookingdates.checkin"));
        System.out.println("jsonPath.getString(\"bookingdates.checkout\") = " + jsonPath.getString("bookingdates.checkout"));
        System.out.println("jsonPath.getString(\"additionalneeds\") = " + jsonPath.getString("additionalneeds"));
        // Assertion
        // 1st way: using matchers (equalTo() or is() method)
        // with equalTo()
        Assert.assertEquals("John",jsonPath.getString("firstname"));
        Assert.assertEquals("Smith",jsonPath.getString("lastname"));
        Assert.assertEquals(111,jsonPath.getInt("totalprice"));
        Assert.assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
        Assert.assertTrue(jsonPath.getBoolean("depositpaid"));




    }




}























