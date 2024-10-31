package get_requests;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get09TR extends JsonPlaceHolderBaseUrl {

    /*
    Given
       https://jsonplaceholder.typicode.com/todos
    When
       Kullanıcı URL'e bir GET request gönderir
    Then
        Status code 200 olmalı
        "Id"leri 190 dan büyük olanları konsola yazdırın
        "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
        "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
        "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
        "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
 */

    @Test
    public void test01(){
          //1 - Set the url
        spec.pathParam("first","todos");

        Response response= RestAssured.given(spec).when()
                .get("{first}");
        //response.prettyPrint();
        System.out.println("============================================");

        JsonPath jsonPath=response.jsonPath();
        List<Objects> id=jsonPath.getList("id");
        //System.out.println("id = " + id);
        System.out.println("======================");

        List<Objects>title=jsonPath.getList("title");
        //System.out.println("title = " + title);
        System.out.println("================");

        List<Objects>completed=jsonPath.getList("completed");
        System.out.println("completed = " + completed);
        System.out.println("=====================");

        //do assertion

        AssertJUnit.assertEquals(200,response.statusCode());

        //"Id"leri 190 dan büyük olanları konsola yazdırın
        List<Objects>jsonList=jsonPath.getList("findAll{it.id > 190}");
        System.out.println("jsonList = " + jsonList);
        System.out.println("=================================");

        // "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
        AssertJUnit.assertEquals(10,jsonList.size());

        System.out.println("=================================");

        //"Id"si 5 ten küçük olan tüm kullanıcıların "userId"lerini yazdırın

        List<Objects>jsonListIdKucukBes=jsonPath.getList("findAll{it.id < 5}.userId");
        System.out.println("jsonListIdKucukBes = " + jsonListIdKucukBes);
        System.out.println("=================================");

       // "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
        AssertJUnit.assertEquals(4,jsonListIdKucukBes.size());
        System.out.println("=================================");

        //"Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        List<Objects>jsonListIdKucukBesIdleri=jsonPath.getList("findAll{it.id < 5}.title");
        System.out.println("jsonListIdKucukBesIdleri = " + jsonListIdKucukBesIdleri);
        System.out.println("=================================");

        //"delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın

        AssertJUnit.assertTrue(jsonListIdKucukBesIdleri.contains("delectus aut autem"));


    }

}























