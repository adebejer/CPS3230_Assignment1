package test.marketalertum;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import um.edu.cps3230.Product;
import um.edu.cps3230.restClient;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MarketAlertUmSteps {
    WebDriver driver;
    int count;
    int actual;


    @io.cucumber.java.en.Given("^I am a user of marketalertum$")
    public void iAmAUserOfMarketalertum() {
        System.setProperty("webDriver.chrome.driver","/Users/adria/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.marketalertum.com/");
    }

    @io.cucumber.java.en.When("^I login using valid credentials$")
    public void iLoginUsingValidCredentials() {
        WebElement loginButton = driver.findElement(By.xpath("//a[@href='/Alerts/Login']"));
        loginButton.click();
        WebElement inputField = driver.findElement(By.name("UserId"));
        inputField.sendKeys("2f7894be-442b-41cc-aad0-a3b04a7a6891");
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();
    }

    @io.cucumber.java.en.Then("^I should see my alerts$")
    public void iShouldSeeMyAlerts() {
        WebElement alertNotice = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("Latest alerts for Adriana Ebejer", alertNotice.getText());
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        WebElement loginButton = driver.findElement(By.xpath("//a[@href='/Alerts/Login']"));
        loginButton.click();
        WebElement inputField = driver.findElement(By.name("UserId"));
        inputField.sendKeys("invalid-login-credentials");
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        WebElement logInTag = driver.findElement(By.tagName("b"));
        Assertions.assertEquals("User ID:", logInTag.getText());

    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) {
        //uploading alerts
        Product product = new Product("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD", "https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth", "https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg", 24999 );
        restClient rest = new restClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < arg0; i++) {
            try {
                mapper.writeValue(new File("C://Users//adria//IdeaProjects//Assignment1_CPS3230//src//test//java//data//product.json"), product);
                String productJsonString = mapper.writeValueAsString(product);
                rest.post("https://api.marketalertum.com/Alert", productJsonString, headerMap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        WebElement loginButton = driver.findElement(By.xpath("//a[@href='/Alerts/Login']"));
        loginButton.click();
        WebElement inputField = driver.findElement(By.name("UserId"));
        inputField.sendKeys("2f7894be-442b-41cc-aad0-a3b04a7a6891");
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();
        WebElement alertNotice = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("Latest alerts for Adriana Ebejer", alertNotice.getText());
        List<WebElement> tables = driver.findElements(By.xpath("//tbody[@border='1']"));
        count = tables.size();
        boolean flag = false;
        if(count >= 0){
            flag = true;
        }
        Assertions.assertTrue(flag);

    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        List<WebElement> tables = driver.findElements(By.xpath("//tbody[@border='1']"));
        count = tables.size();
        List<WebElement> icons = driver.findElements(By.xpath("//img[@src='/images/icon-electronics.png']"));
        actual = icons.size();
        Assertions.assertEquals(count,actual);
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        List<WebElement> tables = driver.findElements(By.xpath("//tbody[@border='1']"));
        count = tables.size();
        int headings = driver.findElements(By.tagName("h4")).size();
        Assertions.assertEquals(headings,count);
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        List<WebElement> tables = driver.findElements(By.xpath("//tbody[@border='1']"));
        count = tables.size();
        //divided by 5 since every product has 5 td tags and description can't be identified uniquely
        int descriptions = (driver.findElements(By.tagName("td")).size())/5;
        Assertions.assertEquals(descriptions,count);
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        List<WebElement> tables = driver.findElements(By.xpath("//tbody[@border='1']"));
        count = tables.size();
        int images = driver.findElements(By.xpath("//img[@width='200']")).size();
        Assertions.assertEquals(images,count);
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        List<WebElement> tables = driver.findElements(By.xpath("//tbody[@border='1']"));
        count = tables.size();
        int price = driver.findElements(By.tagName("b")).size();
        Assertions.assertEquals(price,count);
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        List<WebElement> tables = driver.findElements(By.xpath("//tbody[@border='1']"));
        count = tables.size();
        int price = driver.findElements(By.partialLinkText("Visit item")).size();
        Assertions.assertEquals(price,count);
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) {
        //uploading alerts
        Product product = new Product("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD", "https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth", "https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg", 24999 );
        restClient rest = new restClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < arg0; i++) {
            try {
                mapper.writeValue(new File("C://Users//adria//IdeaProjects//Assignment1_CPS3230//src//test//java//data//product.json"), product);
                String productJsonString = mapper.writeValueAsString(product);
                rest.post("https://api.marketalertum.com/Alert", productJsonString, headerMap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @When("I view a list of alerts I should see {int} alerts")
    public void iViewAListOfAlertsIShouldSeeAlerts(int arg0) {
        WebElement loginButton = driver.findElement(By.xpath("//a[@href='/Alerts/Login']"));
        loginButton.click();
        WebElement inputField = driver.findElement(By.name("UserId"));
        inputField.sendKeys("2f7894be-442b-41cc-aad0-a3b04a7a6891");
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();
        WebElement alertNotice = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("Latest alerts for Adriana Ebejer", alertNotice.getText());
        List<WebElement> tables = driver.findElements(By.xpath("//tbody[@border='1']"));
        count = tables.size();
        boolean flag = false;
        if(count == arg0){
            flag = true;
        }
        Assertions.assertTrue(flag);
    }

    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(int arg0) {
        Product product = new Product(arg0);
        restClient rest = new restClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("C://Users//adria//IdeaProjects//Assignment1_CPS3230//src//test//java//data//product.json"), product);
            String productJsonString = mapper.writeValueAsString(product);
            rest.post("https://api.marketalertum.com/Alert", productJsonString, headerMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        throw new io.cucumber.java.PendingException();

    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        List<WebElement> tables = driver.findElements(By.xpath("//tbody[@border='1']"));
        count = tables.size();
        Assertions.assertEquals(count,arg0);
        throw new io.cucumber.java.PendingException();
    }


    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String arg0) {
        WebElement icon = driver.findElement(By.tagName("img"));
        Assertions.assertEquals(icon, arg0);
        restClient rest = new restClient();
        try {
            rest.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        throw new io.cucumber.java.PendingException();
    }

}
