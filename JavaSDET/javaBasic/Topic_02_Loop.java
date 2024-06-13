package javaBasic;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Loop {
    //final: ko cho phép gán lại giá trị mới
    // không cho các class khác kế thua nó
    public static final String PI="3,145436346";

    public final void clickToElement () {
        // không cho phép overiver lại
    }


    //throw nằm trong try catch
    //throw nằm tren hàm
    public static void clickToButton() throws InterruptedException {
        // gọi cái hàm topic_02 không cần khỏi tạo
        Thread.sleep(5000);
    }

    public static void main (String [] args){
        // Truy cập trực tiếp
        Topic_02_Loop topic02 = new Topic_02_Loop();
        topic02.clickToElement();


        System.out.println("For:");
        for (int i = 1; i <= 10; i++) {
            if (i == 5){
            System.out.println(i);
            break;
            }
        }
        System.out.println("While:");
        int i = 1;
        while (i <=10){ // Kiểm tra trước
            System.out.println(i); // Action sau
            i++;
        }
        System.out.println("Do-While:");
        i = 1;
        do { //thực thi trước
            System.out.println(i);
            i++;
        }while (i <= 10); // Kiểm tra trước
    }
    public boolean isElementDisplayed () {
        WebDriver driver = new FirefoxDriver();
        WebElement element = driver.findElement(By.cssSelector(""));
        boolean status = false;
        try {
            status = element.isDisplayed();
        } catch (NoSuchElementException exception){
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());

        } finally { // Step bắt buộc phải chạy
            // Đóng kết nối vào DB/ Clear dữ liệu
        }
        return status;
    }
}
