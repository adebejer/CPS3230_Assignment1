package um.edu.cps3230;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class Main {

    public static void goToScan(WebDriver driver) {
        driver.get("https://www.scanmalta.com/shop/");
    }

    public static void searchForPhone(WebDriver driver){
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Apple iPhone 14");
        WebElement searchButton = driver.findElement(By.tagName("BUTTON"));
        searchButton.click();
    }

    public static void filterSearch(WebDriver driver){
        WebElement searchOption = driver.findElement(By.id("sorter"));
        searchOption.click();
        WebElement priceOption = driver.findElement(By.xpath("//option[@value='price']"));
        priceOption.click();
    }

    public static void postAPICall(Product product){
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
    }

    public static void getProducts(WebDriver driver){
        List<WebElement> descriptions = driver.findElements(By.xpath("//a[@class='product-item-link']"));
        List<WebElement> Urls = driver.findElements(By.xpath("//a[@class='product-item-link']"));
        List<WebElement> imageUrls = driver.findElements(By.xpath("//img[@class='product-image-photo main-img']"));
        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='price-wrapper ']"));
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setDescription(descriptions.get(i).getText());
            product.setUrl(Urls.get(i).getAttribute("href"));
            product.setImageUrl(imageUrls.get(i).getAttribute("src"));
            product.setPriceInCents(Integer.parseInt(prices.get(i).getAttribute("data-price-amount")+"00"));
            postAPICall(product);
        }
    }


    public static void main(String[] args) {
        WebDriver driver;
        System.setProperty("webDriver.chrome.driver","/Users/adria/Downloads/chromedriver");
        driver = new ChromeDriver();
        goToScan(driver);
        searchForPhone(driver);
        filterSearch(driver);
        getProducts(driver);

    }



}