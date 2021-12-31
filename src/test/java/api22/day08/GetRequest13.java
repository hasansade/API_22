package api22.day08;

import api22.testBase.DummyTestBase;
import api22.testData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyTestBase {

    //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    //Status kodun 200 olduğunu,
    //5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
    //Sondan 2. çalışanın maaşının 106450 olduğunu
    //40,21 ve 19 yaslarında çalışanlar olup olmadığını
    //11. Çalışan bilgilerinin
    //  {
    // “id”:”11”
    // "employee_name": "Jena Gaines",
    //"employee_salary": "90560",
    //"employee_age": "30",
    //"profile_image": "" }
    //} gibi olduğunu test edin.

    @Test
    public void test(){

    spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje=new DummyTestData();

        HashMap<String,Object> expectedDataMap=expectedObje.setUpTestData();

        System.out.println(expectedDataMap);

        Response response=given()
                .accept("application/json")
                        .spec(spec03)
                                .when()
                                        .get("/{parametre1}");

       // response.prettyPrint();

        //de-s

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        System.out.println("actual Data : " + actualDataMap);

//Status kodun 200 olduğunu, (response dan mecburen)
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        //5. Çalışan isminin "Airi Satou" olduğunu

        Assert.assertEquals(expectedDataMap.get("besincicalisan"),
                ((Map)((List)actualDataMap.get("data")).get(4)).get("employee_name"));

//çalışan sayısının 24 olduğunu,

        Assert.assertEquals(expectedDataMap.get("calisansayisi"),
                ((List) actualDataMap.get("data")).size());

        //Sondan 2. çalışanın maaşının 106450 olduğunu
        //actual data dan bize dönen listin size ını alalım
        int dataSize= ((List<?>) actualDataMap.get("data")).size();
        Assert.assertEquals(expectedDataMap.get("sondanikincicalisanmaasi"),
                ((Map)((List<?>) actualDataMap.get("data")).get(dataSize-2)).get("employee_salary"));

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        //once actauldatamap den yasları ayrı bir listeye atıyoruz
        //sonra testData clasında olusturdugumuz arananYaslar listesini icerip icermedigini test ederiz
        List<Integer> actualYasListesi= new ArrayList<>();

        for (int i=0; i<dataSize; i++){
            actualYasListesi.add( ( (Integer) ((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_age")) );
        }

        Assert.assertTrue(actualYasListesi.containsAll((List)expectedDataMap.get("arananyaslar")));


        //11. Çalışan bilgilerinin
        //  {
        // “id”:”11”
        // "employee_name": "Jena Gaines",
        //"employee_salary": "90560",
        //"employee_age": "30",
        //"profile_image": "" }
        //} gibi olduğunu test edin.

        Assert.assertEquals(((Map)expectedDataMap.get("onbirincicalisan")).get("employee_name"),
                ((Map)((List) actualDataMap.get("data")).get(10)).get("employee_name"));

        Assert.assertEquals(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_salary"),
                ((Map) ((List) actualDataMap.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_age"),
                    ((Map)((List) actualDataMap.get("data")).get(10)).get("employee_age"));
        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("profile_image"),
                ((Map)((List<?>) actualDataMap.get("data")).get(10)).get("profile_image"));

    }

}
