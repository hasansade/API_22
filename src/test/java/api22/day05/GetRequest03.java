package api22.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest03 {
    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"

{
    "firstname": "Sally",
    "lastname": "Jones",
    "totalprice": 427,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2020-10-24",
        "checkout": "2021-03-05"
    }
ve firstname'in "Sally"
ve lastname'in "Ericsson"
ve checkin date'in 2018-10-07"
ve checkout date'in 2020-09-30 oldugunu test edin
     */


@Test
    public void test01(){

    String url  = "https://restful-booker.herokuapp.com/booking/7";

    Response response= given().
            accept("application/json").
            when().get(url);

    response.prettyPrint();


  /*
    response.then().assertThat().statusCode(200).
            contentType("application/json")
    .body("firstname", Matchers.equalTo("Sally"))
            .body("lastname", Matchers.equalTo("Jones"))
            .body("totalprice", Matchers.equalTo(427))
            .body("depositpaid", Matchers.equalTo(true))
            .body("bookingdates.checkin", Matchers.equalTo("2020-10-24"))
            .body("bookingdates.checkout", Matchers.equalTo("2021-03-05"));

   */

    //diger bir metodumuz

    response.then().assertThat().
            statusCode(200)
            .contentType(ContentType.JSON)
            .body("firstname" , equalTo("Sally"),
                    "lastname", equalTo("Jones"),
                    "totalprice", equalTo(427),
                    "depositpaid", equalTo(true),
                    "bookingdates.checkin", equalTo("2020-10-24"));

    //Matchers.equalTo metodunda matchers class static import edip,
    //MAtcher.* dememiz yeterlidir

}

}
