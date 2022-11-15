package test.cps3230;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import um.edu.cps3230.Product;

import java.util.List;

public class webTest {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        System.setProperty("webDriver.chrome.driver","/Users/adria/Downloads/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }

    @Test
    public void testScanWebsiteSearchisWorking() {
        //go to the website Scan
        driver.get("https://www.scanmalta.com/shop/");
    }

    @Test
    public void testScanWebsiteisNotWorkingWithChrome(){
        driver.quit();
        System.setProperty("webDriver.edge.driver","/Users/adria/Downloads/edgedriver");
        driver = new EdgeDriver();
        driver.get("https://www.scanmalta.com/shop/");
    }

    @Test
    public void testSearchResults(){
        driver.get("https://www.scanmalta.com/shop/");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Apple iPhone 14");
        WebElement searchButton = driver.findElement(By.tagName("button"));
        searchButton.click();
        WebElement searchResult = driver.findElement(By.className("base"));
        Assertions.assertEquals("Search results for: 'Apple iPhone 14'", searchResult.getText());
    }

    @Test
    public void testFilterSearch(){
        driver.get("https://www.scanmalta.com/shop/");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Apple iPhone 14");
        WebElement searchButton = driver.findElement(By.tagName("button"));
        searchButton.click();
        WebElement searchResult = driver.findElement(By.className("base"));
        Assertions.assertEquals("Search results for: 'Apple iPhone 14'", searchResult.getText());
        WebElement searchOption = driver.findElement(By.id("sorter"));
        searchOption.click();
        WebElement priceOption = driver.findElement(By.xpath("//option[@value='price']"));
        priceOption.click();
    }

    @Test
    public void testGetProductInfo() {

        driver.get("https://www.scanmalta.com/shop/");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Apple iPhone 14");
        WebElement searchButton = driver.findElement(By.tagName("button"));
        searchButton.click();
        WebElement searchResult = driver.findElement(By.className("base"));
        Assertions.assertEquals("Search results for: 'Apple iPhone 14'", searchResult.getText());
        WebElement searchOption = driver.findElement(By.id("sorter"));
        searchOption.click();
        WebElement priceOption = driver.findElement(By.xpath("//option[@value='price']"));
        priceOption.click();
        List<WebElement> descriptions = driver.findElements(By.xpath("//a[@class='product-item-link']"));
        List<WebElement> Urls = driver.findElements(By.xpath("//a[@class='product-item-link']"));
        List<WebElement> imageUrls = driver.findElements(By.xpath("//img[@class='product-image-photo main-img']"));
        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='price-wrapper ']"));
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setDescription(descriptions.get(i).getText());
            product.setUrl(Urls.get(i).getAttribute("href"));
            product.setImageUrl(imageUrls.get(i).getAttribute("src"));
            product.setPriceInCents(Integer.parseInt(prices.get(i).getAttribute("data-price-amount") + "00"));
            System.out.println(product.getAlertType()+" "+product.getHeading() +" "+ product.getDescription()+" "+product.getUrl()+" "+product.getImageUrl()+" "+product.getPostedBy()+" "+product.getPriceInCents());
        }
    }











        //WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id<locator>))
        //WebElement phoneList = driver.findElement(By.xpath("//a[@href='https://www.scanmalta.com/shop/catalogsearch/result/index/?cat=576&q=iPhone']"));
        //phoneList.click();
        //WebElement filterResult = driver.findElement(By.className("filter-value"));
        //Assertions.assertEquals("Phones", filterResult.getText());



}
