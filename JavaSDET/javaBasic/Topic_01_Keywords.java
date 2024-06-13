package javaBasic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Topic_01_Keywords {
    // Có cac hàm non-abstract

    char c = 'a';
    byte bNumber = 10;
    short sNumber = 100;
    int iNumber = 1000;
    long lNumber = 10000;
    float fNumber = 15.7f;
    double dNumber = 45.88d;
    boolean bStatus = true;

    // tham chiếu (Reference Type)
    String fullName = "Automation Test";

    // hàm này ko cần trả về gì hết
    void clickToLoginButton (){
    }

    //Hàm này cần trả về kiểu String
    String getLoginMessage (){
        // Chỉ được return 1 lần
        return "";
    }
    // Bất kì 1 class nào cũng truy cập vào biến được
    public  String address = "Hồ Chí Minh Q12";

    // kế thừa mới dùng được
    protected String city = "Hà Nội";

    // chỉ class này dùng được thôi
    private String phone = "0988999888";

    //Trong cùng package thì đùng được
    String zipCode = "65000";

    //package: define xem class/ interface nằm trong package nào

    WebDriver driver;

    public WebDriver getBrowserDriver (String browserName) {
        if (browserName.equals("firefox")){
            driver = new FirefoxDriver();
        } else if (browserName.equals("chrome")){
            driver = new ChromeDriver();
        } else {
            driver = new SafariDriver();
        }


        switch (browserName) {
            case "firefox":
                driver = new FirefoxDriver();
            case "chrome":
                driver = new ChromeDriver();
            default:
                driver = new SafariDriver();
        }
        return driver;
    }








}
