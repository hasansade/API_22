package api22.day09;

import api22.testBase.DummyTestBase;
import api22.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyTestBase {

    /*
   http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
    */
    @Test
    public void test() {
        spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje=new DummyTestData();
        //dummy testData dan obje oluşturup, setup metodunu cagırıp, map e attık
        HashMap<String, Integer>expectedDataMap= expectedObje.setUpTestData02();
        System.out.println(expectedDataMap);

        Response response=given().accept("application/json")
                .spec(spec03)
                .when()
                .get("/{parametre1}");

        //response.prettyPrint();

        //Data type string Integer yapamam, body den bize string vs döneceği için Object yaptık
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        System.out.println("Actual : " + actualDataMap);

        Assert.assertEquals(expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());

        //En yüksek maaşın 725000 olduğunu,
        List<Integer> maasListesi =new ArrayList<Integer>();

        int dataSize =((List)actualDataMap.get("data")).size();
        for (int i =0; i<dataSize; i++ ) {
           maasListesi.add ((Integer) (((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_salary")));

        }
        Collections.sort(maasListesi);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasListesi.get(dataSize-1));

        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"),maasListesi.get(dataSize-2));

        //en kucuk yas
        List<Integer> yasListesi =new ArrayList<Integer>();

        for (int i=0; i<dataSize; i++){
            yasListesi.add((Integer) (((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age")));
        }
        Collections.sort(yasListesi);

        Assert.assertEquals(expectedDataMap.get("enKucukYas"), yasListesi.get(0));


        //Json path yontemi

        JsonPath json=response.jsonPath();

        List<Integer> maasListesiJson = json.getList("data.employee_salary");

        Collections.sort(maasListesiJson);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"), maasListesiJson.get(maasListesiJson.size()-1));
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), maasListesiJson.get(maasListesiJson.size()-2));

        //En küçük yaşın 19 olduğunu,

        List<Integer> yasListesiJson=json.getList("data.employee_age");
        Collections.sort(yasListesiJson);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"), yasListesiJson.get(0));

    }
}