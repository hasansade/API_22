package api22.day07;

import api22.testBase.JsonPlaceHolderTestBase;
import api22.testData.JsonPlaceHolderTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest11TestData extends JsonPlaceHolderTestBase {

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


        JsonPlaceHolderTestData expectedObje =new JsonPlaceHolderTestData();


        HashMap<String,Object> expectedData= (HashMap<String, Object>) expectedObje.setUpTestData();



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
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("ttile"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("false"));

//3.yontem
        //deserializesion
        //opject mapper ve pojo classlar
    }



}
