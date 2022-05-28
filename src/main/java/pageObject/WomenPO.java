package pageObject;

import commons.BasePage;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import pageUIs.WomenUI;

import java.util.Locale;

@DefaultUrl("http://automationpractice.com/index.php")
public class WomenPO extends PageObject {
    private BasePage basePage;
    private WebDriver driver;
    private String fistnameTshirt = "";


    public WomenPO(WebDriver driver) {
        this.driver = driver;
        basePage = BasePage.getBasePage();
    }

    public void goToMenuTshirt(){
        basePage.moveTo(WomenUI.WOMEN_BTN);
        basePage.clickElement(WomenUI.WOMEN_TSHIRT_BTN);
    }

    public void getNameTextOfTheFirstProductDisplayedOnThePage(){
        fistnameTshirt = basePage.getElementText(WomenUI.NAME_FIRST_TSHIRT);
        System.out.println("AAAAAAAAAAAAAAAA" + fistnameTshirt);
    }

    public void senkeysTheSameProductName(){
       basePage.senKeyElement(WomenUI.SEARCH_NAME_TSHIRT_FIELD,fistnameTshirt);
    }
    public void clickSearchButton(){
        basePage.clickElement(WomenUI.SEARCH_NAME_TSHIRT_BTN);
    }

    public boolean verifySameProductIsDisplayedOnSearchedPageWithSameDetailsWhichWereDisplayedOnTShirtSPage(){
        return basePage.verifyElementText(WomenUI.NAME_FIRST_TSHIRT,fistnameTshirt);
    }

    public boolean verifySameProductIsDisplayedOnSearchedPage(){
        System.out.println("NOTIFY: "+basePage.getElementText(WomenUI.SEARCH_NOTIFY_FIELD));
        String firstNameTshirt = fistnameTshirt.toUpperCase();
        System.out.println("FIRSTNAME: "+ firstNameTshirt);

        return basePage.verifyElementText(WomenUI.SEARCH_NOTIFY_FIELD,firstNameTshirt);
//        return basePage.isDisplayedElement(WomenUI.SEARCH_NOTIFY_FIELD);
    }

    public boolean verifyTheFirstProductDisplayedOnThePage(){
        return basePage.isDisplayedElement(WomenUI.NAME_FIRST_TSHIRT);
    }

    public boolean verifyMoreButtonWillBeDisplayed(){
        return basePage.isDisplayedElement(WomenUI.MORE_BTN);
    }
    public void clickOnMoreButton(){
        basePage.clickElement(WomenUI.MORE_BTN);
    }

    public void clickColor(){
        basePage.clickElement(WomenUI.BLUE_COLOR);
    }

    public void clickAddToCart(){
        basePage.clickElement(WomenUI.ADD_TO_CART_BTN);
        basePage.sleepInSecond(3);
    }

    public void clickProceedToCheckout(){
        basePage.waitForElementClickable(WomenUI.PROCESS_TO_CHECKOUT_BTN);
        basePage.clickElement(WomenUI.PROCESS_TO_CHECKOUT_BTN);
        basePage.clickElement(WomenUI.PROCESS_TO_CHECKOUT_SECOND_BTN);
        basePage.clickElement(WomenUI.PROCESS_TO_CHECKOUT_THIRD_BTN);
        basePage.clickElement(WomenUI.AGREE_BTN);
        basePage.clickElement(WomenUI.PROCESS_TO_CHECKOUT_FORD_BTN);
    }

    public void clickPayByBankWire(){
        basePage.clickElement(WomenUI.PAYMENT_BY_BANKWIRE);
//        basePage.sleepInSecond(2);
    }

    public void clickConfirmOrder(){
        basePage.clickElement(WomenUI.CONFIRM_ORDER);
        basePage.sleepInSecond(2);
    }

    public boolean verifyThatProductIsOrdered(String message){
       return basePage.verifyElementText(WomenUI.SUCCESS_NOTIFY_CONFIRM_ORDER,message);
//        basePage.sleepInSecond(2);
    }
}
