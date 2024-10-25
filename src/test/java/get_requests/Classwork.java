package get_requests;
import base_urls.RegressBaseUrl;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Classwork extends RegressBaseUrl{
    /*
Senaryo: "Kullanıcı Yönetim Sistemi API Testi"

Kullanılacak API: ReqRes API  https://reqres.in/

ReqRes, HTTP istekleri ile çalışan basit bir kullanıcı yönetimi API'sidir.
Bu API üzerinden kullanıcılar oluşturabilir, listeleyebilir, güncelleyebilir ve silebilirsiniz.

Görev 1: Kullanıcı Listesini Getir

API Endpoint: GET https://reqres.in/api/users?page=2

Test Adımları:
İkinci sayfadaki kullanıcı listesini al.
HTTP yanıt kodunun 200 olduğunu doğrula.
Yanıtın JSON yapısındaki kullanıcı sayısını doğrula (örneğin, en az 1 kullanıcı olmalı).
İlk kullanıcının adını (örneğin "Michael") JSON Path kullanarak doğrula.

 */

    @Test
    public void getUserList() {
        //1 set the url
        spec.pathParams("first", "api", "second" , "users")
                .queryParam("page",2);

        given(spec)
                .when()
                .get("{first}/{second}")
                .then()
                .statusCode(200)
                .body("data.size()", greaterThan(0))
                .body("data[0].first_name", equalTo("Michael"))
                .log().all();
    }
}









