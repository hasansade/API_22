package api22.day06;

import api22.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummyTestBase {


    //http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
    //   1) Butun calisanlarin isimlerini consola yazdıralim
    //   2) 3. calisan kisinin ismini konsola yazdıralim
    //   3) Ilk 5 calisanin adini konsola yazdiralim
    //   4) En son calisanin adini konsola yazdiralim


    @Test
    public void test02(){

        spec03.pathParam("parametre1", "employees");

        Response response=given().accept("application/json").
        spec(spec03).
                when().get("/{parametre1}");
        //response.prettyPrint();

        JsonPath jsonPath= response.jsonPath();
        //getString veya getList
        System.out.println(jsonPath.getList("data.employee_name"));
        System.out.println(jsonPath.getString("data.employee_name"));

        //3.calısan ismi
        System.out.println(jsonPath.getString("data[2].employee_name"));
        System.out.println(jsonPath.getString("data.employee_name[2]"));

        //ilk 5 isim

        System.out.println(jsonPath.getString("data.employee_name[0,1,2,3,4]"));


        //son isim
        System.out.println(jsonPath.getString("data[-1].employee_name"));

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Ashton Cox", jsonPath.getString("data[2].employee_name"));
        Assert.assertEquals("Doris Wilder", jsonPath.getString("data[-1].employee_name"));

    }

}
