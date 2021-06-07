package com.github.alllef;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AddToCartTest {
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://dominos.ua/en/kyiv/");
    }

    public void mainPageCartTest(int addNumber) {
        mainPage.addToCart(addNumber);

        String expectedQuantityResult = "0" + addNumber;
        if (addNumber >= 10) expectedQuantityResult = String.valueOf(addNumber);

        Assert.assertEquals("Quantity of cart is not the same as expected quantity", expectedQuantityResult, mainPage.getCartQuantity());
        Assert.assertEquals("Price in cart is not the same as expected price", mainPage.getPrice(addNumber), mainPage.getCartPrice(), 0.1);
    }


    @Test
    public void testMainPageCart() {
        mainPageCartTest(5);
        mainPageCartTest(3);
        mainPageCartTest(0);
        mainPageCartTest(11);
    }
}
