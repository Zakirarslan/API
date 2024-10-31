package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


public class JsonPlaceHolderBaseUrl {
    // This class is created to prevent repetition of pre-conditions (urls, content-type, header, token etc..)

    protected RequestSpecification spec;


    @Before //bu method her test methpdundan önce çalışıp, request yapılandırmasını sağlar
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(ContentType.JSON)
                .build();
    }


}