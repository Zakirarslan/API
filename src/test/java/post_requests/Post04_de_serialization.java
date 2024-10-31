package post_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Post04_de_serialization extends HerokuAppBaseUrl {
    /*
    1) https://restful-booker.herokuapp.com/booking
   2) {
       "firstname" : "Jim",
       "lastname" : "Brown",
       "totalprice" : 999,
       "depositpaid" : true,
       "bookingdates" : {
           "checkin" : "2024-01-01",
           "checkout" : "2024-01-01"
       },
       "additionalneeds" : "Extra pillows please"
   }
When
   I send POST Request to the Url
Then
   Status code is 200
   And response body should be like {
          "bookingid": 4809,
          "booking": {
              "firstname": "Jim",
              "lastname": "Brown",
              "totalprice": 999,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2024-01-01",
                  "checkout": "2024-01-01"
              },
              "additionalneeds": "Extra pillows please"
          }
          */
    @Test
    public void post04(){
        //Step 1:Set the URL
        spec.pathParams("p1", "booking");

        //Step 2: Set the expected data
        //Step 2.1: Inner JSON - bookingdates
        Map<String, String> bookingMap = HerokuAppTestData.bookingDatesMapper("2024-01-01", "2024-01-01");
        System.out.println("bookingMap = " + bookingMap);
        // Step 2.2: Outer JSON - expected data
        Map<String, Object> payload = HerokuAppTestData.herokuAppMapper("Jim", "Brown", 999, true, bookingMap,
                "Extra pillows please");
        System.out.println("payload = " + payload);

        //Step 3: Send the post request and get the response
        Response response = given(spec).when().body(payload).post("{p1}");
        response.prettyPrint();



        //Do Assertion
        //1st way: Body Assertion
        response.then().statusCode(200)
                .body("booking.firstname",equalTo(payload.get("firstname")))
                .body("booking.lastname",equalTo(payload.get("lastname")))
                .body("booking.totalprice",equalTo(payload.get("totalprice")))
                .body("booking.depositpaid",equalTo(payload.get("depositpaid")))
                .body("booking.bookingdates.checkin",equalTo(bookingMap.get("checkin")))
                .body("booking.bookingdates.checkout",equalTo(bookingMap.get("checkout")))
                .body("booking.additionalneeds",equalTo(payload.get("additionalneeds")));
        //2nd way: Assert with JsonPath
        JsonPath jsonPath = response.jsonPath();
        assertEquals(200, response.statusCode());
        assertEquals(payload.get("firstname"), jsonPath.getString("booking.firstname"));
        assertEquals(payload.get("lastname"), jsonPath.getString("booking.lastname"));
        assertEquals(payload.get("totalprice"), jsonPath.getInt("booking.totalprice"));
        assertEquals(payload.get("depositpaid"), jsonPath.getBoolean("booking.depositpaid"));
        assertEquals(bookingMap.get("checkin"), jsonPath.getString("booking.bookingdates.checkin"));
        assertEquals(bookingMap.get("checkout"), jsonPath.getString("booking.bookingdates.checkout"));
        assertEquals(payload.get("additionalneeds"), jsonPath.getString("booking.additionalneeds"));

        //3th way: Assert with Deserialized Maps

        Map<String,Object> actualData=response.as(HashMap.class); //de-serialization

        assertEquals(200,response.statusCode());
        assertEquals(payload.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(payload.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(payload.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(payload.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingMap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingMap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
        assertEquals(payload.get("additionalneeds"),((Map)actualData.get("booking")).get("additionalneeds"));


      //  "bookingid": 4809,
      //          "booking": {
      //      "firstname": "Jim",
      //              "lastname": "Brown",
      //              "totalprice": 999,
      //              "depositpaid": true,
      //              "bookingdates": {
      //                  "checkin": "2024-01-01",
      //                  "checkout": "2024-01-01"
      //      },
      //      "additionalneeds": "Extra pillows please"
        }

}






























