package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.AuthenticateContactList;

public class ContactListBaseUrl {

    protected RequestSpecification spec;

    @Before  // This annotation will run before each test method
    public void setUp() {
        String baseUrl = "https://restful-booker.herokuapp.com";
        spec = new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .addHeader("Authorization", AuthenticateContactList.generateToken())
                .build();
    }
}
