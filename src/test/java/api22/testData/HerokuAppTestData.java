package api22.testData;

import java.util.HashMap;

public class HerokuAppTestData {

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
   public HashMap<String, Object> setUpTestData(){
       HashMap<String ,Object> bookingdates=new HashMap<String, Object>();
       bookingdates.put("checkin","2017-08-17");
       bookingdates.put("checkout","2017-12-23");
       HashMap<String ,Object> expectedData=new HashMap<String, Object>();
       expectedData.put("firstname","Mary");
       expectedData.put("lastname","Jones");
       expectedData.put("totalprice",963);
       expectedData.put("depositpaid",true);
       expectedData.put("bookingdates",bookingdates);
       return expectedData;
   }

}
