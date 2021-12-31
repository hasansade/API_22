package api22.day07;

import api22.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends JsonPlaceHolderTestBase {

    //https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
    // Dönen response un
    // Status kodunun 200, dönen body de,
    //       "completed": değerinin false
    //       "title”: değerinin “quis ut nam facilis et officia qui”
    //       "userId" sinin 1 ve header değerlerinden
    // "Via" değerinin “1.1 vegur” ve
    //       "Server" değerinin “cloudflare” olduğunu test edin…

    @Test
    public void test02(){

        spec01.pathParams("parametre1", "todos", "parametre2", 2);

        HashMap<String,Object> expectedData=new HashMap<String,Object>();
expectedData.put("Status Code", 200);
expectedData.put("Via","1.1 vegur");
expectedData.put("completed", false);
expectedData.put("title", "quis ut nam facilis et officia qui");
expectedData.put("userId", 1);
expectedData.put("Server", "cloudflare");

        System.out.println(expectedData);
        System.out.println("--------------------------");
        Response response=given().accept("application/json").
                spec(spec01).
                when().get("/{parametre1}/{parametre2}");
response.prettyPrint();

//1. yontem Matchers class ile assertion

response.then().assertThat().statusCode((Integer) expectedData.get("Status Code")).
        headers("via", equalTo(expectedData.get("Via")),
                "Server", equalTo(expectedData.get("Server"))).
        body("userId", equalTo(expectedData.get("userId")),
                "title", equalTo(expectedData.get("title")),
                "completed", equalTo(expectedData.get("completed")));


//2.yontem

        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedData.get("Status Code"),response.statusCode());
        Assert.assertEquals(expectedData.get("Via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"), response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));

//3.yontem
        //deserializesion


        HashMap<String,Object> actualData=response.as(HashMap.class);
        //response dan gelen yanıtı actualdataya aktardık .as ile data typler eşitlendi

        System.out.println(actualData);
        //de-s ile yine body bilgilerini alabilirim(jsonpath gibi), statuscode header response ile alınır
        //de-s ile statuscode header alınmaz

        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}
