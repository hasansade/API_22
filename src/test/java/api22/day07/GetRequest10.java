package api22.day07;

import api22.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends DummyTestBase {

    //http://dummy.restapiexample.com/api/v1/employees
    //url ine bir istek gönderildiğinde
    //Dönen response un
    // Status kodunun 200,
    // 1)10’dan büyük tüm id’leri ekrana yazdırın ve
    //10’dan büyük 14 id olduğunu,
    // 2)30’dan küçük tüm yaşları ekrana yazdırın ve
    //  bu yaşların içerisinde en büyük yaşın 23 olduğunu
    // 3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
    //  bunların içerisinde “Charde Marshall” olduğunu test edin

@Test
    public void test01(){

    spec03.pathParam("parametre1", "employees");

    Response response=given().
            accept("application/json").
            spec(spec03).
            when().get("/{parametre1}");

    response.prettyPrint();

    JsonPath jsonPath=response.jsonPath();

    Assert.assertEquals(200, response.getStatusCode());

    //groovy javanın alt dilidir, loop kullanmadan gelen response daki
    //degerleri bir sarta baglayabiliriz

    List<Integer> idList= jsonPath.getList("data.findAll{it.id>10}.id");

    System.out.println(idList);

    //10’dan büyük 14 id olduğunu,

    Assert.assertEquals(14, idList.size());

    // 30’dan küçük tüm yaşları ekrana yazdırın ve

    List<Integer> ageList= jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
    System.out.println(ageList);

//  bu yaşların içerisinde en büyük yaşın 23 olduğunu
    Collections.sort(ageList);
Assert.assertEquals((Integer)23, ageList.get(ageList.size()-1));


//Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın

   List<String> isimListesi= jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
    System.out.println(isimListesi);


    Assert.assertTrue(isimListesi.contains("Charde Marshall"));
}
}
