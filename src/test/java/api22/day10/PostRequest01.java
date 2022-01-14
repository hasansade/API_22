package api22.day10;

import api22.testBase.DummyTestBase;
import api22.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyTestBase {

/*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
 "name":"Ahmet Aksoy",
 "salary":"1000",
 "age":"18",
  }
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin ,
{
 "status": "success",
 "data": {
 “id”:…
 },
 "message": "Successfully! Record has been added."
 }
olduğunu test edin
     */
    @Test
    public void test(){

        spec03.pathParam("parametre1", "create");

        //post request yaparken biz body göndermek zorundayız, testdata classında oluşturduğumuz request
        //body i burada çağırıyoruz.
        //obje olusturup cagırdık

        DummyTestData obje=new DummyTestData();

        HashMap<String,String>requestBodyMap=obje.setUpRequestBody();
        //beklediğimiz response buraya aldık
        HashMap<String,Object>expectedDataMap= obje.setUpExpectedData();

        Response response=given()
                .accept("application/json")
                .spec(spec03).auth().basic("admin", "password123")
        .body(requestBodyMap)
                .when().post("/{parametre1}");
        response.prettyPrint();

        //De-serialization
    HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));

        //JsonPath

        JsonPath json =response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("status"),json.getString("status"));
        Assert.assertEquals(expectedDataMap.get("message"),json.getString("message"));
    }


}
