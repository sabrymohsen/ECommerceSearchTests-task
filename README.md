
# ECommerceSearchTests

## Overview
This project contains Selenium-based automated test scripts for testing the search and filtering features of an e-commerce website (e.g., Noon). The tests include searching for products using keywords, applying filters (price range and brand), and verifying that the displayed results meet the filter criteria.

## Prerequisites
- **Java Development Kit (JDK)**: Version 8 or above
- **Maven**: For managing dependencies
- **Chrome WebDriver**: Version compatible with your Chrome browser
- **TestNG**: Testing framework used for structuring and running the tests

## Project Setup

### 1. Clone the Repository
Clone this repository to your local machine.

```bash
git clone https://github.com/your-username/ecommerce-search-tests.git
cd ecommerce-search-tests
```

### 2. Install Dependencies



This will download all required libraries specified in the `pom.xml` file, such as:
- **Selenium WebDriver**: For browser automation
- **TestNG**: For testing structure and assertions




## Running the Tests

### Test Structure
The test suite includes two main test cases:
1. **testSimpleSearchWithKeywordAndEnter**: Tests searching for a product by keyword and verifying the search results.
2. **testSearchWithFilters**: Tests searching for a product, applying price and brand filters, and verifying that all displayed results meet the filter criteria.


### Expected Output
The test results will display in the console. Each assertion will confirm whether:
- The search results contain the expected keyword.
- The filtered results meet the price range and brand requirements.

## Script Details

The main test class is `ECommerceSearchTests.java`. Below are the details of each method:



### `testSimpleSearchWithKeywordAndEnter()`
1. Searches for a product using the keyword "laptop."
2. Verifies that the search results contain the keyword "laptop."
3. Checks that the results container is displayed after pressing Enter.

### `testSearchWithFilters()`
1. Searches for a product using the keyword "laptop."
2. Applies a price filter (500 - 15000 EGP).
3. Applies a brand filter (HP).
4. Verifies that all displayed results meet the price range and brand criteria.

### tearDown() *(Optional)*
This method can be used to close the browser after test completion. Uncomment this method to enable it:

```java
@AfterClass
public void tearDown() {
    driver.quit();
}
```

## Troubleshooting
- Make sure that the locators in the test script match the current website's HTML structure. These locators may need to be updated if the website changes.
- If tests are failing due to timing issues, consider replacing `Thread.sleep()` with explicit waits (`WebDriverWait`) for a more reliable approach.


# ECommerceSearchTests-task
