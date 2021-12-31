package api22.day08;

import api22.testBase.HerokuAppTestBase;
import api22.testData.HerokuAppTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends HerokuAppTestBase {


/*
       {
    "firstname": "Susan",
    "lastname": "Wilson",
    "totalprice": 619,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2016-01-12",
        "checkout": "2017-02-19"
    }
}
    */

@Test
    public void test(){

//url oluşturalım
    spec02.pathParams("parametre1", "booking", "parametre2", 1);

//expectedData oluşturalım, ki testdata classında oluşturduk

    HerokuAppTestData expectedobje=new HerokuAppTestData();

    HashMap<String,Object> expectedeDataMap= expectedobje.setUpTestData();

    System.out.println(expectedeDataMap);

    //request gonderelim

    Response response=given().accept("application/json").
            spec(spec02).
            when().get("/{parametre1}/{parametre2}");

    response.prettyPrint();

//actualdatayı alalım
    HashMap<String,Object> actualDataMap=response.as(HashMap.class);
    System.out.println(actualDataMap);


    Assert.assertEquals(expectedeDataMap.get("firstname"), actualDataMap.get("firstname"));
    Assert.assertEquals(expectedeDataMap.get("lastname"), actualDataMap.get("lastname"));
Assert.assertEquals(expectedeDataMap.get("totalprice"),actualDataMap.get("totalprice"));
Assert.assertEquals(expectedeDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));

    Assert.assertEquals(     ((Map)expectedeDataMap.get("bookingdates")).get("checkin"),
            ((Map)actualDataMap.get("bookingdates")).get("checkin"));

    Assert.assertEquals(     ((Map)expectedeDataMap.get("bookingdates")).get("checkout"),
            ((Map)actualDataMap.get("bookingdates")).get("checkout"));


        //2.yol json path

    JsonPath jsonPath=response.jsonPath();

    Assert.assertEquals(expectedeDataMap.get("firstname"), jsonPath.getString("firstname"));
    Assert.assertEquals(expectedeDataMap.get("lastname"), jsonPath.getString("lastname"));
    Assert.assertEquals(expectedeDataMap.get("totalprice"), jsonPath.getInt("totalprice"));
    Assert.assertEquals(expectedeDataMap.get("depositpaid"), jsonPath.getBoolean("depositpaid"));

    Assert.assertEquals(     ((Map)expectedeDataMap.get("bookingdates")).get("checkin"),
            jsonPath.getString("bookingdates.checkin"));

    Assert.assertEquals(     ((Map)expectedeDataMap.get("bookingdates")).get("checkout"),
            jsonPath.getString("bookingdates.checkout"));

}

}
