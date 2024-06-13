package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class BasePage {
//   1 - Access Modifier: public/ protected/ private/ default
//   2 - Kiểu dữ liệu của hàm (Data type): void/ int/ String/ boolean/ WebElement/ List<WebElement>/..
//      Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm
//   3 - Tên hàm: Đặt tên có nghĩa theo chức năng đang cần viết
//      Convention tuân theo chuẩn của từng ngôn ngữ lập trình (Java)
//      camelCase: từ đầu tiên viết thường - chữ cái đầu tiên của các từ tiếp theo sẽ viết hoa
//   4 - Có tham số hay ko (tùy vào chức năng cần viết)
//   5 - Kiểu dữ liệu trả về cho hàm
//      Nếu như có return dữ liệu thì sẽ khớp vs kiểu dữ liệu ở số 2
//      Nếu như có return thì nó là cái step cuối cùng

    //Biến toàn cục: Global
    String fullName;

    public String getFullName() {
        // Local: Biến cục bộ: được sinh ra trong hàm, trong 1 khối lệnh
        String fullName = null;

            //trong phạm vi khối lệnh (block code)
        for (int i = 0; i < 10; i++) {
            int n = 1;

            if (n > 0) { //cục bộ
                int x = 10;
            }

        }

        return fullName;
    }

    public boolean isElementDisplayed () {
        return  driver.findElement(By.cssSelector("")).isDisplayed();
    }

    private WebElement driver;

    public void clickToElement () {
        driver.findElement(By.cssSelector("")).click();
    }

    public String getElementText (WebDriver driver, String locator) {
       return getElement(driver,locator).getText();
    }
    // define tham số
    //Giá trị sử dụng trong hàm sẽ truyền từ bên ngoài thông qua các tham số
    public void sendkeyToElement(String valueToSendkey){
        driver.findElement(By.cssSelector("")).sendKeys(valueToSendkey);
    }

    //Tuân theo nguyên tắc đóng gói- cách 2 ko cần new lên
    //Hàm static có thể truy cap trực tiếp từ phạm vi class mà ko cần khởi tạo nó lên
    public static BasePage getBasePage (){
        return new BasePage();
    }




    // Bài tập
    // common funtion hàm dùng chung cho cac class


    public  void openPageUrl(WebDriver driver,String url) {
        driver.get(url);
    }

    public String getTitle (WebDriver driver){
        return driver.getTitle();
    }

    public String getPageUrl (WebDriver driver) {
        return  driver.getCurrentUrl();
    }

    public String getPageSoure (WebDriver driver) {
       return driver.getPageSource();
    }
    public void backToPage (WebDriver driver){
        driver.navigate().back();
    }
    public void forwardToPage (WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage (WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence (WebDriver driver) {
       return new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.alertIsPresent());
    }
    public void acceptToAlert (WebDriver driver) {
        // Chỉ switch ko wait
       // driver.switchTo().alert().accept();

        // wait có xuất hiện alert rồi mới switch vào
        waitAlertPresence(driver).accept();
    }
    public void cancelToAlert (WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }
    public String getTextAlert (WebDriver driver) {
        return  waitAlertPresence(driver).getText();
    }
    public void acceptToAlert (WebDriver driver,String keysToSend) {
        waitAlertPresence(driver).sendKeys(keysToSend);
    }


    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
    public WebElement getElement (WebDriver driver, String locator) {
        return  driver.findElement(By.xpath(locator));
    }
    public List<WebElement> getListElement (WebDriver driver,String locator){
        return driver.findElements(By.xpath(locator));
    }

    public void clickToElement (WebDriver driver,String locator) {
        getElement(driver,locator).click();
    }

    public void sendkeyToElement (WebDriver driver, String locator,String keysToSend) {
        getElement(driver,locator).sendKeys(keysToSend);
    }

    public void selectedInDropdown (WebDriver driver, String locator, String textItem){
        new Select(getElement(driver,locator)).selectByVisibleText(textItem);
    }
    public String  getSelectedItemDropdown (WebDriver driver, String locator){
       return new Select(getElement(driver,locator))
                .getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple (WebDriver driver, String locator){
        return new Select(getElement(driver,locator)).isMultiple();
    }
    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver,parentLocator).click();
        sleepInSeconds(2);
        new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }
    public String getAttributeValue(WebDriver driver, String locator, String name){
        return  getElement(driver, locator).getAttribute(name);
    }
    public By getByXPath (String locator){
       return By.xpath(locator);
    }
    //
    public void getTextElementJs (WebDriver driver, String locator) {
        getElement(driver,locator).getText();
    }
    public void getCssValue (WebDriver driver, String locator,String propertyName) {
        getElement(driver,locator).getCssValue(propertyName);
    }
    public String getHexaColorFromRGBA (String rgbaValue) {
       return Color.fromString(rgbaValue).asHex().toUpperCase();
    }
    public int getListElementNumber (WebDriver driver, String locator) {
       return getListElement(driver, locator).size();
    }
    public void checkToCheckboxRadio (WebDriver driver, String locator){
        if (!getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }
    public void uncheckToCheckBox (WebDriver driver, String locator){
        if (getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();

        }
    }

    public boolean isElementDisplayed (WebDriver driver, String locator){
        return getElement(driver, locator).isDisplayed();
    }
    public boolean isElementEnable (WebDriver driver, String locator){
        return getElement(driver, locator).isEnabled();
    }
    public boolean isElementSelected (WebDriver driver, String locator){
        return getElement(driver, locator).isSelected();
    }
    public void switchToIFrame (WebDriver driver, String locator){
        driver.switchTo().frame(getElement(driver, locator));
    }
    public void switchToDefaultPage (WebDriver driver, String locator){
        driver.switchTo().defaultContent();
    }
    public  void hoverToElement (WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }
    public  void clickAndHoldToElement(WebDriver driver, String locator) {
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }
    public  void doubleClickAndHoldToElement (WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }
    public  void rightClickAndHoldToElement (WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }
    public  void dragAndDropElement (WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver,sourceLocator),getElement(driver, targetLocator)).perform();
    }
    public  void pressKeyToElement (WebDriver driver, String locator, Keys keys) {
        new Actions(driver).sendKeys(getElement(driver, locator),keys).perform();
    }
    public  void scrollToElement (WebDriver driver, String locator, Keys keys) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }
    //xem lại các làm javascriptExecutor

    public void scrollToBottomByJsPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElementJS(WebDriver driver,String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTopJs(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDownJs(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOMJs(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(locator));
    }

    public void removeAttributeInDOMJs(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(WebDriver driver,String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOMJs(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(WebDriver driver,String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(WebDriver driver,String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(locator));
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
    //Wait
    public void waitForElementVisible (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByXPath(locator)));
    }
    public void waitForElementPresent (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(getByXPath(locator)));
    }
    public void waitForElementInvisible (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByXPath(locator)));
    }
    public void waitForElementClickable (WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getByXPath(locator)));
    }

    public void sleepInSeconds (long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
