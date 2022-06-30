package StepsDefinition;

import common.Constant;
import common.GlobalVariable;
import common.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseStepDef {

    String URI;
    RequestSpecification request = RestAssured.given();
    static Response response;

    public void sendAPIRequest(String httpMethod) {
        switch (httpMethod.toUpperCase()) {
            case "POST":
                response = request.when().post(URI);
                break;
            case "GET":
                response = request.when().get(URI);
                break;
            default:
                System.err.println("The method " + httpMethod + " is not supported, please use \"GET\" or \"POST\"");
        }
        GlobalVariable.put(Constant.RESPONSE, response.asString());
        System.out.println("{Response body}: " + response.asString());
//        response.prettyPrint();
    }

    public void prepareURI(String apiName) {
        switch (apiName){
            case "SearchFlight":
                URI = PropertyReader.getProperty(Constant.URLs.HOST_URI) + "flights/flight/search";
                System.out.println("{URI}: " + URI + "?" + Constant.RequestParams.QUERY + "=" + GlobalVariable.getValue(Constant.PARAMETER));
                break;
            case "AsyncSearchResult":
                URI = PropertyReader.getProperty(Constant.URLs.HOST_URI) + "flights/flight/async-search-result";
                System.out.println("{URI}: " + URI);
                break;
            default:
                System.err.println("The API " + apiName + " is not supported, please use \"SearchFlight\" or \"AsyncSearchResult\" APIs");
        }
    }

    public void prepareQueryParameter(String key, String value){
        request.queryParam(key,value);
    }

    public void prepareAPIBody(String body) {
        request.header(Constant.CONTENT_TYPE, "application/json");
        request.body(body);
    }

    public String getDateAsNumber(int addedDays){
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, addedDays);
        return objSDF.format(c.getTime());
    }

    public static void lenientAssert(String expected) throws JSONException {
        JSONAssert.assertEquals(expected, response.asString(), JSONCompareMode.LENIENT);
    }

    public static void strictAssert(String expected) throws JSONException {
        JSONAssert.assertEquals(expected, response.asString(), JSONCompareMode.STRICT);
    }

}
