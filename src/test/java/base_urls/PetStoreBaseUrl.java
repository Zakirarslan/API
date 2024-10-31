package base_urls;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class PetStoreBaseUrl {

    /*
    AMAÇ :
    ==> Her testten önce çalışarak base url ve content type gibi ortak request yapılandırmalarını tek merkezden
    bie defa yapmak, böylece testlerin bakımının daha kolay olmasını sağlamak
    ==> kod tekrarını önlemek
     */

    protected RequestSpecification spec;

    @BeforeMethod //bu method her test methpdundan önce çalışıp, request yapılandırmasını sağlar
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .setContentType(ContentType.JSON)
                .build();
    }
}
