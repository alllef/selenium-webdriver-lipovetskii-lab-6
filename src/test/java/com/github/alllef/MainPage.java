package com.github.alllef;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    @FindBy(css = "#root > div.content > div.dp-product-list__container > div:nth-child(2) > div.dp-product-list > div:nth-child(1) > div > div.dp-product-block__description > div.dp-product-block__price-row > div.dp-product-block__btn-wrap > button")
    private WebElement chickenKebabToCartBtn;

    @FindBy(css = "#root > div.content > div.dp-product-list__container > div:nth-child(2) > div.dp-product-list > div:nth-child(1) > div > div.dp-product-block__description > div.dp-product-block__price-row > div.dp-product-block__btn-wrap > div > button:nth-child(3)")
    private WebElement chickenKebabAddMoreToCartBtn;

    @FindBy(css = "#root > div.content > div.dp-product-list__container > div:nth-child(2) > div.dp-product-list > div:nth-child(1) > div > div.dp-product-block__description > div.dp-product-block__price-row > div.dp-product-block__btn-wrap > div > button:nth-child(1)")
    private WebElement chickenKebabRemoveFromCartBtn;

    @FindBy(css = "#root > div.content > div.dp-product-list__container > div:nth-child(2) > div.dp-product-list > div:nth-child(1) > div > div.dp-product-block__description > div.dp-product-block__price-row > div.dp-product-block__price-block > div > span.dp-product-block__price")
    private WebElement chickenKebabPrice;

    @FindBy(css = "#root > div.dp-header-desktop > div.main-header.main-header_sticky > div > div.main-header__cart > div > div > div > div > div")
    private WebElement cartQuantity;

    @FindBy(css = "#root > div.dp-header-desktop > div.main-header.main-header_sticky > div > div.main-header__cart > div > div > div > div.button-cart__info-price > p")
    private WebElement cartPrice;

    private int cartNumber = 0;

    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void addToCart(int number) {
        if (cartNumber > number) {
            while (cartNumber != number) {
                chickenKebabRemoveFromCartBtn.click();
                cartNumber--;
            }
        } else {

            if (cartNumber == 0 && number != 0) {
                chickenKebabToCartBtn.click();
                cartNumber++;
            }

            while (cartNumber != number) {
                chickenKebabAddMoreToCartBtn.click();
                cartNumber++;
            }
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public double getPrice(int number) {
        return Double.parseDouble(chickenKebabPrice.getText()) * number;
    }

    public double getCartPrice() {
        if (cartNumber == 0) return 0;
        return Double.parseDouble(cartPrice.getText().split(" ")[0]);
    }

    String getCartQuantity() {
        return cartQuantity.getText();
    }
}
