package ge.automation.stepDefinitions;

import ge.automation.driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class MyStepdefs {

    WebDriver driver;

public MyStepdefs(){
    driver = DriverManager.getDriver();
}

    @Given("browser is launched and {string} page open")
    public void browserIsLaunchedAndPageOpen(String baseUrl) {
        driver.get(baseUrl);
    }

    @When("user enters valid username {string} and password {string}")
    public void userEntersValidUsernameAndPassword(String username, String password) {

        WebElement usernameEl = driver.findElement(By.id("user-name"));
        WebElement passwordEl = driver.findElement(By.id("password"));

       usernameEl.clear();
       usernameEl.sendKeys(username);
       passwordEl.clear();
       passwordEl.sendKeys(password);
    }

    @And("click on login button")
    public void clickOnLoginButton() {
        WebElement clickButtonEl = driver.findElement(By.id("login-button"));

        clickButtonEl.click();
        
    }

    @Then("user redirected to product page {string}")
    public void userRedirectedToProductPage(String expectedUrl) {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,expectedUrl);
    }

    @And("user adds to cart {string}")
    public void userAddsToCart(String product) {
        product = product.toLowerCase().replace(" ", "-");
        String productXpath = "//button[@id='add-to-cart-" + product + "']";
        driver.findElement(By.xpath(productXpath)).click();

    }

    @And("navigate to cart page")
    public void navigateToCartPage() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    @And("cart should contain {string}")
    public void cartShouldContain(String product) {
        WebElement findProduct = driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
        findProduct.getText();
        Assert.assertEquals(findProduct.getText(), product);
    }
}

