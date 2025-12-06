package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private WebDriver driver;

    @FindBy(className = "product_sort_container")
    private WebElement sortSelect;

    // prices as displayed elements
    @FindBy(css = ".inventory_item_price")
    private List<WebElement> priceElements;

    @FindBy(css = ".inventory_item")
    private List<WebElement> items;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isOpen() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }

    public void selectSortOption(String optionVisibleText) {
        sortSelect.click();
        // simple select by visible text - use option locator
        WebElement option = sortSelect.findElement(By.xpath(".//option[text()='" + optionVisibleText + "']"));
        option.click();
    }

    public List<Double> getPrices() {
        return priceElements.stream()
                .map(WebElement::getText)
                .map(s -> s.replace("$", "").trim())
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }

    public void addItemToCartByName(String productName) {
        // find item by name, then click its add button
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement addBtn = item.findElement(By.cssSelector("button.btn_primary.btn_inventory, button.btn_inventory"));
                addBtn.click();
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public void goToCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }
}
