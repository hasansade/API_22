package api22.day06;

import api22.testBase.HerokuAppTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest07 extends HerokuAppTestBase {


    //https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
    //        HTTP Status Code’unun 200
    //        ve response content type’inin “application/JSON” oldugunu
    //            ve response body’sinin asagidaki gibi oldugunu test edin
    //                {"firstname": Sally,
    //                "lastname": "Wilson",
    //                "totalprice": 487,
    //                "depositpaid": true,
    //                "bookingdates": {     "checkin": "2019-07-28",
    //                                                    "checkout":"2021-11-26" }
    //

@Test
    public void test01(){

    spec02.pathParams("parametre1","booking", "parametre2",5);

    Response response=given()
            .accept("application/json")
            .spec(spec02)
            .when().get("/{parametre1}/{parametre2}");

    response.prettyPrint();

    JsonPath jsonPath=response.jsonPath();

    //Jsonpath bize sadece body döndürür
//expected-actual
    Assert.assertEquals("Sally", jsonPath.getString("firstname"));
Assert.assertEquals("Wilson", jsonPath.getString("lastname"));
Assert.assertEquals(849, jsonPath.getInt("totalprice"));
Assert.assertEquals(true, jsonPath.getBoolean("depositpaid"));

Assert.assertEquals("2019-07-28", jsonPath.getString("bookingdates.checkin"));
Assert.assertEquals("2021-11-26", jsonPath.getString("bookingdates.checkout"));


}

}
