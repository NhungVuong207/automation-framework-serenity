package stepDefinitions;

import commons.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObject.LogInPO;

public class LogInStep {
    private LogInPO logInPO;


    @Given("^Get login page Url$")
    public void getLoginPageUrl() {
        logInPO.open();
        Assert.assertTrue(logInPO.getTitle().contains("My Store"));
    }

    @When("Open to Register Page")
    public void openToRegisterPage() {
        logInPO.goToregisterPage();
        Assert.assertTrue(logInPO.verifyRegisterPage());
    }

    @When("Input to Username")
    public void inputToUsername() {
        logInPO.inputToUsername();
    }

    @And("Click to Submit button on page")
    public void clickToSubmitButtonOnPage() {
        logInPO.clickToSubmitButtonOnPage();
    }

    @Then("Click to MRS radio button")
    public void clickToRadioButton() {
        logInPO.clickToRadioButtonTittle();
    }

    @And("Input to {string} textbox with value {string}")
    public void inputToTextboxWithValue(String fieldName, String value) {
        logInPO.enterToTextboxByFieldName(fieldName, value);
    }

    @And("Input to {string} with value {string}")
    public void inputToWithValue(String fieldName, String value) {
        logInPO.enterToSelectByFieldName(fieldName, value);
    }


    @And("Click to Register button")
    public void clickToRegisterButton() {
        logInPO.clickToRegisterButton();
    }

    @Then("Success message displayed with {string}")
    public void successMessageDisplayedWith(String successMessage) {
        Assert.assertTrue(logInPO.verifySuccessRegister(successMessage));
    }


    @And("Click to Login button")
    public void clickToLoginButton() {
        logInPO.clickToLoginButton();
    }

    @And("Error message {string} displayed with {string}")
    public void verifyErrorMessageDisplayed(String message, String fieldName) {
        Assert.assertTrue(logInPO.verifyErrorRegister(message, fieldName));
    }

    @And("Error message {string} is displayed")
    public void verifyFieldDisplayed(String fieldname) {
        Assert.assertTrue(logInPO.verifyErrorField(fieldname));
    }

    @Then("Click to Register button without filling information")
    public void clickToRegisterButtonWithoutFillingInformation() {
        logInPO.clickToRegisterButtonWithoutFillingInformation();
    }
}

