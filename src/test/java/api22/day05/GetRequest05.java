package api22.day05;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest05 {
    //http://dummy.restapiexample.com/api/v1/employees url'ine
//    accept type'i "application/json" olan GET request'i yolladigimda
//    gelen response'un
//    status kodunun 200                                                                                        ve content type'inin "application/json"
//   ve employees sayisinin 24
//   ve employee'lerden birinin "Garrett Winters"
//   ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin

@Test
    public void test02(){

    String url ="http://dummy.restapiexample.com/api/v1/employees";

    Response response=given().accept("application/json")
            .when().get(url);

    response.prettyPrint();

    response.then().assertThat().statusCode(200)
            .contentType("application/json")
            .body("data.id", hasSize(24),
                    "data.employee_name", hasItem("Garrett Winters"),
                    "data.employee_age", hasItems(22,63,66));

    }

}
