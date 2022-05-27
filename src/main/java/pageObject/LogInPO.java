package pageObject;

import com.ibm.icu.impl.Assert;
import commons.BasePage;
import commons.GVs;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.LogInUI;

import static com.ibm.icu.impl.Assert.*;

@DefaultUrl("http://automationpractice.com/index.php")

public class LogInPO extends PageObject {
private WebDriver driver;
private BasePage basePage;
private String currentUrl = LogInUI.CURRENT_URL;
private String emailCreate = LogInUI.EMAIL_CREATE;

//private String successMessage = LogInUI.SUCCESS_MESS;

    public LogInPO(WebDriver driver) {

        this.driver = driver;
        basePage = BasePage.getBasePage();
    }


    public void goToregisterPage(){
        basePage.clickElement(LogInUI.LOGIN_FIEL);
    }
    public boolean verifyRegisterPage(){
        return basePage.getElement(LogInUI.LOGIN_PAGE_HEADING).getText().contains(LogInUI.AUTHENTICATION);
    }
    public void inputToUsername(){
        basePage.senKeyElement(LogInUI.EMAIL_CREATE_INPUT,emailCreate);
    }
    public void clickToSubmitButtonOnPage(){
        basePage.clickElement(LogInUI.SUBMIT_BTN_LOGIN);
        basePage.sleepInSecond(20);
    }
    public void clickToRadioButtonTittle(){
//        basePage.waitForElementClickable(LogInUI.MRS_TITTLE);
        basePage.clickElement(LogInUI.MRS_TITTLE);
        basePage.scrollToElement(LogInUI.ADDRESS_TITLE);
    }


    public void enterToTextboxByFieldName(String fieldName, String value) {
//        if (fieldName.equals("Password")) {
//            basePage.removeAttributeInDOM(driver, LogInUI.DYNAMIC_TEXTBOX, "type", fieldName);
//        }
        basePage.senkeyToElement(LogInUI.DYNAMIC_TEXTBOX,value,fieldName);
    }

    public void enterToSelectByFieldName (String fieldName, String value) {

        basePage.selectDropDownByValue(LogInUI.SELECT_DAY, value, fieldName);
    }

    public void clickToRegisterButton(){
        basePage.clickElement(LogInUI.REGISTER_BTN);
    }

    public boolean verifySuccessRegister(String successMessage){
       return basePage.verifyElementText(LogInUI.MY_ACCOUNT,successMessage);
    }


    public void clickToLoginButton() {
        basePage.clickElement(LogInUI.LOGIN_BTN);
    }

    public boolean verifyErrorRegister(String errorMessage, String fieldName){
        return basePage.verifyElementText(LogInUI.ERROR_MESSAGE_REGISTER,errorMessage,fieldName);
    }

    public boolean verifyErrorField(String fieldName){
        return basePage.isDisplayedElement(LogInUI.ERROR_MESSAGE_FIELD,fieldName);
    }

    public void clickToRegisterButtonWithoutFillingInformation(){
        basePage.scrollToElement(LogInUI.REGISTER_BTN);
        basePage.clickElement(LogInUI.REGISTER_BTN);
            basePage.sleepInSecond(3);
    }
}
