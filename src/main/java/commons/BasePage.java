package commons;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.webdriver.stubs.AlertStub;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage extends PageObject {
    private WebDriver driver;
    private WebDriverWait explicitwait;
    private Alert alert;
    private Duration shortTimeout = GVs.SHORT_TIMEOUT;
    private Duration longTimeout = GVs.LONG_TIMEOUT;
    private Select select;
    private Actions action;
    private JavascriptExecutor jsExecutor;

    public static BasePage getBasePage() {
        return new BasePage();
    }

    public void openPageURL(String pageUrl) {
        getDriver().get(pageUrl);
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public String getPageSource() {
        return getDriver().getPageSource();
    }

    public void backPage() {
        getDriver().navigate().back();
    }

    public void forwarPage() {
        getDriver().navigate().forward();
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public Alert waitAlertPresence() {
        explicitwait = new WebDriverWait(getDriver(), shortTimeout);
        return explicitwait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert() {
        alert = waitAlertPresence();
        alert.accept();
        sleepInSecond(2);
    }

    public void cancelAlert() {
        alert = waitAlertPresence();
        alert.dismiss();
    }

    public void senKeyAlert(String value) {
        alert = waitAlertPresence();
        alert.sendKeys(value);
        sleepInSecond(2);
    }

    public String getTextAlert() {
        alert = waitAlertPresence();
        return alert.getText();
    }

    public void switchWindowByID(String windowID) {
        Set<String> allWindowIDs = getDriver().getWindowHandles();

        for (String id : allWindowIDs) {

            if (!id.equals(windowID)) {
                getDriver().switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String pageTitle) {
        Set<String> allWindowIDs = getDriver().getWindowHandles();
        for (String id : allWindowIDs) {
            getDriver().switchTo().window(id);
            String actualTitle = getDriver().getTitle().trim();
            if (actualTitle.equals(pageTitle)) {
                break;
            }
        }
    }

    public void closeAlltabWithoutParent(String parentID) {
        Set<String> allWindowIDs = getDriver().getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(parentID)) {
                getDriver().switchTo().window(id);
                getDriver().close();
            }
        }
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement getElement(String locator) {
        return getDriver().findElement(getByXpath(locator));
    }

    public WebElement getElement(String locator,String... params) {
        locator = getDynamicLocator(locator,params);
        return getDriver().findElement(getByXpath(locator));
    }

    public String getDynamicLocator(String locator, String... params) {
        return String.format(locator, (Object[]) params);
    }

    public List<WebElement> getElements(String locator) {
        return getDriver().findElements(getByXpath(locator));
    }

    public void sleepInSecond(long shortTimeSleep) {
        try {
            Thread.sleep(shortTimeSleep * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickElement(String locator) {
//        waitForElementClickable(locator);
        getElement(locator).click();
    }



    public void senKeyElement(String locator, String value) {
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
    }

    public void senkeyToElement(String locator, String value, String...params) {
        locator = getDynamicLocator(locator, params);
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
    }

    public int getSizeElements(String locator) {
        return getElements(locator).size();
    }

    public int getSizeElements(String locator, String...params) {
        return getElements(getDynamicLocator(locator, params)).size();
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
        for (Cookie cookie : allCookies) {
            driver.manage().addCookie(cookie);
        }
    }

    public void selectDropDownByText(String locator, String itemText) {
        select = new Select(getElement(locator));
        select.selectByVisibleText(itemText);
    }

    public void selectDropDownByValue(String locator, String value) {
        select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public void selectDropDownByValue(String locator, String value, String...params) {
        select = new Select(getElement(locator,params));
        select.selectByValue(value);
    }

    public void selectDroDownByIndex(String locator, int index) {
        select = new Select(getElement(locator));
        select.selectByIndex(index);
    }
    public String getSelectedItemDropdown(String locator){
        select = new Select(getElement(locator));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedItemDropdown(String locator, String... params){
        locator = getDynamicLocator(locator, params);
        select = new Select(getElement(locator));
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropdown(String parentLocator, String childItemLocator, String expectedItem) {
        getElement(parentLocator).click();
        sleepInSecond(1);

        explicitwait = new WebDriverWait(getDriver(), shortTimeout);
        List<WebElement> allItems = explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) getDriver();
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getElementAttribute(String locator, String attributeName) {
        return getElement(locator).getAttribute(attributeName);
    }

    public String getElementAttribute(String locator, String attributeName, String... params) {
        locator = getDynamicLocator(locator, params);
        return getElement(locator).getAttribute(attributeName);
    }
    public String getElementText(String locator) {
        return getElement(locator).getText().trim();
    }

    public String getElementText(String locator, String...params) {
        return getElement(getDynamicLocator(locator, params)).getText().trim();
    }

    public String getElementCss(String locator, String cssValue) {
        return getElement(locator).getCssValue(cssValue);
    }

    public String getElementCss(String locator, String cssValue, String... params) {
        locator = getDynamicLocator(locator, params);
        return getElement(locator).getCssValue(cssValue);
    }

    public String convertRgbaToHex(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }


    public void checkTheCheckboxOrRadio(String locator, String... params) {
        locator = getDynamicLocator(locator, params);
        if (!isElementSelected(getDriver(), locator)) {
            getElement(locator).click();
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(locator).isSelected();
    }
    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(getElement(locator));
        return select.isMultiple();
    }

    public boolean isSelectedElement(WebDriver driver, String locator) {
        return getElement(locator).isSelected();
    }

    public boolean isDisplayedElement( String locator) {
        return getElement(locator).isDisplayed();
    }

    public boolean isDisplayedElement( String locator, String... param) {
        locator = getDynamicLocator(locator,param);
        return getElement(locator).isDisplayed();
    }

    public boolean isEnableElement(String locator) {
        return getElement(locator).isEnabled();
    }

    public void checkTheCheckboxOrRadio(String locator) {
        if (!isSelectedElement(getDriver(), locator)) {
            getElement(locator).click();
        }
    }

    public void uncheckTheCheckbox(String locator) {
        if (isSelectedElement(getDriver(), locator)) {
            getElement(locator).click();
        }
    }

    public WebDriver switchToIframeByElement(String locator) {
        return getDriver().switchTo().frame(getElement(locator));
    }

    public WebDriver switchToDefaultContent() {
        return getDriver().switchTo().defaultContent();
    }

    public void hoverToElement(String locator) {
        action = new Actions(getDriver());
        action.moveToElement(getElement(locator)).perform();
    }

    public void hoverToElement(String locator, String... params) {
        action = new Actions(getDriver());
        action.moveToElement(getElement(getDynamicLocator(locator, params))).perform();
    }

    public void doubleClickToElement(String locator) {
        action = new Actions(getDriver());
        action.doubleClick(getElement(locator)).perform();
    }

    public void rightClickToElement(String locator) {
        action = new Actions(getDriver());
        action.contextClick(getElement(locator)).perform();
    }

    public void dragAndDropElement(String sourceLocator, String targetLocator) {
        action = new Actions(getDriver());
        action.dragAndDrop(getElement(sourceLocator),getElement(targetLocator)).perform();
    }

    public void pressKeyToElement(String locator, Keys key) {
        action = new Actions(getDriver());
        action.sendKeys(getElement(locator), key).perform();
    }
    public void pressKeyToElement(String locator, Keys key, String...params) {
        action = new Actions(getDriver());
        locator = getDynamicLocator(locator, params);
        action.sendKeys(getElement(locator), key).perform();
    }

    public Object executeForBrowser(String javaScript) {
        jsExecutor = (JavascriptExecutor) getDriver();
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        jsExecutor = (JavascriptExecutor) getDriver();
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(String textExpected) {
        jsExecutor = (JavascriptExecutor) getDriver();
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(String locator) {
        jsExecutor = (JavascriptExecutor) getDriver();
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS( String locator) {
        jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
    }

    public void scrollToElement(String locator) {
        jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public void removeAttributeInDOM( String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove, String... params) {
        jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(getDynamicLocator(locator, params)));
    }

    public boolean areJQueryAndJSLoadedSuccess() {
        explicitwait = new WebDriverWait( getDriver(), longTimeout);
        jsExecutor = (JavascriptExecutor) getDriver();

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitwait.until(jQueryLoad) && explicitwait.until(jsLoad);
    }

    public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
        explicitwait = new WebDriverWait(driver, longTimeout);
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
        return explicitwait.until(jQueryLoad);
    }

    public String getElementValidationMessage(String locator) {
        jsExecutor = (JavascriptExecutor) getDriver();
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        jsExecutor = (JavascriptExecutor) getDriver();
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(String locator) {
        explicitwait = new WebDriverWait(getDriver(), longTimeout);
        explicitwait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementVisible(String locator, String...params) {
        explicitwait = new WebDriverWait(getDriver(), longTimeout);
        explicitwait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
    }

    public void waitForAllElementVisible(String locator) {
        explicitwait = new WebDriverWait(getDriver(), longTimeout);
        explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public void waitForElementInvisible(String locator) {
        explicitwait = new WebDriverWait(getDriver(), shortTimeout);
        explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementInvisible(String locator, String...params) {
        explicitwait = new WebDriverWait(getDriver(), shortTimeout);
        explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
    }

    public void waitForElementClickable(String locator) {
        explicitwait = new WebDriverWait(getDriver(), longTimeout);
        explicitwait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public void waitForElementClickable(String locator, String...params) {
        explicitwait = new WebDriverWait(getDriver(), longTimeout);
        explicitwait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));
    }


    public boolean verifyElementText(String locator, String message) {
        return getElement(locator).getText().contains(message);
    }

    public boolean verifyElementText(String locator, String message, String...params ) {
        locator = getDynamicLocator(locator,params);
        return getElement(locator).getText().contains(message);
    }


}










