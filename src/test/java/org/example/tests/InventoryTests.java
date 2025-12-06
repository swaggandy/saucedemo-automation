package org.example.tests;

import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class InventoryTests extends BaseTest {

    private final String USER = "standard_user";
    private final String PASS = "secret_sauce";

    @Test
    public void testSortByPriceLowToHigh() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login(USER, PASS);

        InventoryPage inv = new InventoryPage(driver);
        Assert.assertTrue(inv.isOpen(), "Inventory page should be open after login");

        // choose "Price (low to high)" option — on saucedemo visible text is "Price (low to high)"
        inv.selectSortOption("Price (low to high)");

        // small wait for UI to update (could use explicit wait)
        Thread.sleep(500);

        List<Double> prices = inv.getPrices();
        // check that prices are sorted ascending
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                    "Prices are not sorted ascending: " + prices);
        }
    }

    @Test
    public void testAddToCartAndVerifyCartContents() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login(USER, PASS);

        InventoryPage inv = new InventoryPage(driver);
        Assert.assertTrue(inv.isOpen(), "Inventory page should be open after login");

        // Add a known product by name
        String productName = "Sauce Labs Backpack";
        inv.addItemToCartByName(productName);

        // go to cart
        inv.goToCart();

        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.getCartSize(), 1, "Cart should have 1 item");
        List<String> names = cart.getCartItemNames();
        Assert.assertTrue(names.contains(productName), "Cart should contain the added product");
    }
}
