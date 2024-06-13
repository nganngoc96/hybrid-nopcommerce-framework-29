package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_I_Initial {
    private WebDriver driver;
    BasePage basePage; //khởi tạo = declare
    String firstname, lastname, day, month, year, emailAddress, companyName, password;

    @BeforeClass
    public void beforeClass (){
        driver = new FirefoxDriver();
        basePage = new BasePage(); // khai báo

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        emailAddress = "thomasmuller" +generateRandomNumber()+ "@gmail.de";
    }
    @Test
    public void TC_01_Register () {
        basePage.waitForElementClickable(driver,"//a[@class='ico-register']");
        basePage.clickToElement(driver,"//a[@class='ico-register']");

        basePage.waitForElementClickable(driver,"//input[@id='gender-female']");
        basePage.clickToElement(driver,"//input[@id='gender-female']");

        basePage.sendkeyToElement(driver,"//input[@id='FirstName']","Thomas");
        basePage.sendkeyToElement(driver,"//input[@id='LastName']","Thomas");

        basePage.selectedInDropdown(driver,"//select[@name='DateOfBirthDay']","10");
        basePage.selectedInDropdown(driver,"//select[@name='DateOfBirthMonth']","August");
        basePage.selectedInDropdown(driver,"//select[@name='DateOfBirthYear']","1986");

        basePage.sendkeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendkeyToElement(driver,"//input[@id='Company']","Bayer Munich");
        basePage.sendkeyToElement(driver,"//input[@id='Password']","123456789");
        basePage.sendkeyToElement(driver,"//input[@id='ConfirmPassword']","123456789");

        basePage.waitForElementClickable(driver,"//button[@id='register-button']");
        basePage.clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='result']"),"Your registration completed");
    }
    @Test
    public void TC_02_Login (){
        basePage.waitForElementClickable(driver,"//a[@class='ico-login']");
        basePage.clickToElement(driver,"//a[@class='ico-login']");

        basePage.sendkeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendkeyToElement(driver,"//input[@id='Password']","123456789");

        basePage.waitForElementClickable(driver,"//button[contains(@class,'login-button')]");
        basePage.clickToElement(driver,"//button[contains(@class,'login-button')]");

        Assert.assertTrue(basePage.isElementDisplayed(driver,"//a[@class='ico-account' and text()='My account']"));

    }
    @Test
    public void  TC_03_Login (){

        basePage.waitForElementClickable(driver,"//a[@class='ico-login']");
        basePage.clickToElement(driver,"//a[@class='ico-login']");

        Assert.assertTrue(basePage.isElementSelected(driver,"//input[@id='gender-female']"));

        Assert.assertEquals(basePage.getAttributeInDOMJs(driver,"//input[@id='FirstName']","value"),"Thomas");
        Assert.assertEquals(basePage.getAttributeInDOMJs(driver,"//input[@id='LastName']","value"),"Muller");

        Assert.assertEquals(basePage.getSelectedItemDropdown(driver,"//select[@name='DateOfBirthDay'"),"10");
        Assert.assertEquals(basePage.getSelectedItemDropdown(driver,"//select[@name='DateOfBirthMonth'"),"August");
        Assert.assertEquals(basePage.getSelectedItemDropdown(driver,"//select[@name='DateOfBirthYear'"),"1986");

        Assert.assertEquals(basePage.getAttributeInDOMJs(driver,"//input[@id='Company']","value"),"Bayer Munich");
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
    private int generateRandomNumber () {
        return new Random().nextInt(99999);
    }


}
