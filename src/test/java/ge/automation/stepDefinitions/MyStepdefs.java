package ge.automation.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class MyStepdefs {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void teardown(){
        driver.quit();
    }

    @Given("browser is launched and {string} page open")
    public void browserIsLaunchedAndPageOpen(String baseUrl) {
        driver.get(baseUrl);
    }

    @When("user enters valid username {string} and password {string}")
    public void userEntersValidUsernameAndPassword(String username, String password) {

       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        WebElement usernameEl = driver.findElement(By.id("user-name"));

       usernameEl.clear();
       usernameEl.sendKeys(username);

       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        WebElement passwordEl = driver.findElement(By.id("password"));
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

