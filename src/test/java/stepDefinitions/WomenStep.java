package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObject.WomenPO;

public class WomenStep {
    private WomenPO womenPO;

    @Given("Go to login page Url")
    public void goToLoginPageUrl() {
        womenPO.open();
        Assert.assertTrue(womenPO.getTitle().contains("My Store"));
    }

    @When("Click to menu T-shirts")
    public void clickToMenuTShirts() {
        womenPO.goToMenuTshirt();
    }

    @And("Get Name of the first product displayed on the page.")
    public void getNameOfTheFirstProductDisplayedOnThePage() {
      womenPO.getNameTextOfTheFirstProductDisplayedOnThePage();
    }

    @And("Enter the same product name in the search bar present on top of page")
    public void enterTheSameProductNameInTheSearchBarPresentOnTopOfPage() {
        womenPO.senkeysTheSameProductName();
    }

    @And("Click search button")
    public void clickSearchButton() {
        womenPO.clickSearchButton();
    }

    @Then("Validate that same product is displayed on searched page with same details which were displayed on T-Shirt's page")
    public void validateThatSameProductIsDisplayedOnSearchedPageWithSameDetailsWhichWereDisplayedOnTShirtSPage() {
        Assert.assertTrue(womenPO.verifySameProductIsDisplayedOnSearchedPage());
        Assert.assertTrue(womenPO.verifySameProductIsDisplayedOnSearchedPageWithSameDetailsWhichWereDisplayedOnTShirtSPage());
    }

    @And("The first product displayed on the page.")
    public void verifyTheFirstProductDisplayedOnThePage() {
        Assert.assertTrue(womenPO.verifyTheFirstProductDisplayedOnThePage());
    }


    @And("MORE button will be displayed, click on MORE button")
    public void verifyMoreButtonWillBeDisplayedClickOnMOREButton() {
        Assert.assertTrue(womenPO.verifyMoreButtonWillBeDisplayed());
        womenPO.clickOnMoreButton();
    }

    @And("Select color")
    public void selectColor() {
        womenPO.clickColor();
    }

    @And("Click Add to Cart")
    public void clickAddToCart() {
        womenPO.clickAddToCart();
    }

    @Then("Click Proceed to checkout")
    public void clickProceedToCheckout() {
        womenPO.clickProceedToCheckout();

    }

    @And("Choose payment by bank wire")
    public void choosePaymentByBankWire() {
        womenPO.clickPayByBankWire();
    }


    @And("Make sure that Product is ordered with notify {string}")
    public void verifyThatProductIsOrderWithNotify(String message) {
        womenPO.clickConfirmOrder();
        Assert.assertTrue(womenPO.verifyThatProductIsOrdered(message));
    }
}
