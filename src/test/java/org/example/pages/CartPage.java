package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private WebDriver driver;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getCartItemNames() {
        return cartItems.stream()
                .map(elem -> elem.findElement(By.className("inventory_item_name")).getText())
                .collect(Collectors.toList());
    }

    public List<Double> getCartItemPrices() {
        return cartItems.stream()
                .map(elem -> elem.findElement(By.className("inventory_item_price")).getText().replace("$", "").trim())
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }

    public int getCartSize() {
        return cartItems.size();
    }
}

