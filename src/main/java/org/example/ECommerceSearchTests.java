package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class ECommerceSearchTests {
    WebDriver driver;

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://noon.com/egypt-en");
    }

    @Test
    public void testSimpleSearchWithKeywordAndEnter() throws InterruptedException {
        // Test Case 1: Simple Search with Keyword and Enter Key

        WebElement searchBar = driver.findElement(By.cssSelector("#searchBar"));
        searchBar.click();
        searchBar.sendKeys("laptop");

        // Verify that the displayed results contain the keyword "laptop"
        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@id=\"__next\"]/div/header/div[2]/div/div[2]/div[2]/div/div/div/div[2]/div/div"));
        for (WebElement result : searchResults) {
            Assert.assertTrue(result.getText().toLowerCase().contains("laptop"),
                    "Each result contains the keyword 'laptop'");
        }
        searchBar.sendKeys(Keys.RETURN);
        Thread.sleep(3000);
        WebElement resultsContainer = driver.findElement(By.cssSelector("div[class='sc-c7c319e8-5 egjcSl'] h1"));
        Assert.assertTrue(resultsContainer.isDisplayed(), "Search results are displayed");
    }

    @Test
    public void testSearchWithFilters() throws InterruptedException {

        // Test Case 2: Search with Keyword, Apply Filters, and Verify Results

        WebElement searchBar = driver.findElement(By.cssSelector("#searchBar"));
        searchBar.sendKeys("laptop");
        searchBar.sendKeys(Keys.RETURN);
        Thread.sleep(3000);
        WebElement resultsContainer = driver.findElement(By.cssSelector("div[class='sc-c7c319e8-5 egjcSl'] h1"));
        Assert.assertTrue(resultsContainer.isDisplayed(), "Search results are displayed");

        // Apply a price range filter (e.g., $500 - $15000)

        WebElement minPrice = driver.findElement(By.xpath("//input[@name='min']"));
        WebElement maxPrice = driver.findElement(By.xpath("//input[@name='max']"));
        WebElement GO = driver.findElement(By.xpath("//button[normalize-space()='go']"));
        minPrice.clear();
        maxPrice.clear();
        minPrice.sendKeys("500");
        maxPrice.sendKeys("15000");
        GO.click();
        // Apply the filter
        Thread.sleep(10000);

        // Apply a brand filter (e.g., "HP")

        WebElement brandFilterCheckbox = driver.findElement(By.xpath("//label[@for='filters-brand-hp']"));
        brandFilterCheckbox.click();
        Thread.sleep(10000);

        // Verify the brand in the product title

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='sc-63bc8e9b-7 hZVQUI grid']"));
        for (WebElement product : products) {

            WebElement titleElement = product.findElement(By.xpath(".//div[@data-qa=\"product-name\"]"));
            String titleText = titleElement.getText().toLowerCase();
            Assert.assertTrue(titleText.contains("hp"), "Product title does not contain 'HP': " + titleText);

            // Verify the price is within the specified range

            WebElement priceElement = product.findElement(By.xpath(".//strong[@class='amount']"));
            String priceText = priceElement.getText().replace("EGP", "").replace(",", "").trim();
            double price = Double.parseDouble(priceText);
            Assert.assertTrue(price >= 500 && price <= 15000, "Product price is out of range: " + price);
        }



    }
//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }
}

