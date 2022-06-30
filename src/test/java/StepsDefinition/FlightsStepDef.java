package StepsDefinition;

import common.Constant;
import common.GlobalVariable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.junit.Assert;

public class FlightsStepDef extends BaseStepDef {

    @Given("^Prepare the (.*) API URI\\.$")
    public void prepareTheSearchFlightAPIURI(String apiName) {
        prepareURI(apiName);
    }

    @And("^Search for a flight using (.*) as a query parameter\\.$")
    public void searchForAFlightUsingAsAQueryParameter(String queryValue) {
        prepareQueryParameter(Constant.RequestParams.QUERY, queryValue);
        GlobalVariable.put(Constant.PARAMETER, queryValue);
    }

    @And("^Search for round trip flights with origin (.*),  destination (.*), depart date with \\+?(-?\\d+) added days, return date with \\+?(-?\\d+) added days, class (.*) and (\\d+) adult\\.$")
    public void searchForAFlightsWithOriginDestinationDepartDateReturnDateClassAndAdult(String origin, String destination, int departDate, int returnDate, String classType, int adult) {
        String queryValue = origin + "-" + destination + "/" + getDateAsNumber(departDate) + "/" + getDateAsNumber(returnDate) + "/" + classType + "/" + adult + "Adult";
        GlobalVariable.put(Constant.PARAMETER, queryValue);
        prepareQueryParameter(Constant.RequestParams.QUERY, queryValue);
    }

    @When("^Send the API request using (.*) http method\\.$")
    public void sendTheAPIRequestUsingHttpMethod(String httpMethod) {
        sendAPIRequest(httpMethod);
    }

    @Then("^Verify that the response code is (\\d+)\\.$")
    public void verifyThatTheResponseCodeIs(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("^I should have the below values in the received response\\.$")
    public void iShouldHaveTheBelowValuesInTheReceivedResponse(String body) throws JSONException {
        lenientAssert(body);
    }

    @And("^Prepare the request body\\.$")
    public void prepareTheRequestBody() {
        prepareAPIBody(GlobalVariable.getValue(Constant.RESPONSE).toString());
    }
}
