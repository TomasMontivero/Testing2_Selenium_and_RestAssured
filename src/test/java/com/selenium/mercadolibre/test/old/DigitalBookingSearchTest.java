package com.selenium.mercadolibre.test.old;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DigitalBookingSearchTest {

    private WebDriver driver;

    @Test
    public void testSearch() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        //options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        options.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.mercadolibre.com.ar/");
        Thread.sleep(1000);

        WebElement searchBox = driver.findElement(By.id("cb1-edit"));
        searchBox.clear();
        searchBox.sendKeys("iPhone 13");
        Thread.sleep(1000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        WebElement min = driver.findElement(By.xpath("//*[@id=\"root-app\"]/div/div[2]/aside/section/div[6]/ul/li[5]/form/div[1]/div/label/div/input"));
        min.clear();
        min.sendKeys("256");
        WebElement max = driver.findElement(By.xpath("//*[@id=\"root-app\"]/div/div[2]/aside/section/div[6]/ul/li[5]/form/div[2]/div/label/div/input"));
        max.clear();
        max.sendKeys("256");
        Thread.sleep(1000);

        WebElement filterButton = driver.findElement(By.xpath("//*[@id=\"root-app\"]/div/div[2]/aside/section/div[6]/ul/li[5]/form/div[3]/button"));
        filterButton.click();
        Thread.sleep(2000);

        //WebElement celular = driver.findElement(By.xpath("//*[@id=\"root-app\"]/div/div[2]/section/ol/li[1]/div/div/div[2]/div[2]"));
        WebElement celular = driver.findElement(By.className("ui-search-item__title"));
        celular.click();
        Thread.sleep(2000);

        //WebElement botonComprar = driver.findElement(By.xpath("//*[@id=\"buybox-form\"]/div[5]/div/button[1]"));
        WebElement botonComprar = driver.findElement(By.className("andes-button--loud"));
        botonComprar.click();
        Thread.sleep(2000);

        WebElement login = driver.findElement(By.xpath("//*[@id=\"root-app\"]/div/div[1]/div/div[2]"));
        String res = login.getText();

        System.out.println("Resultado login: " + res);
        assertTrue(res.contains("¡Hola! Para comprar, ingresá a tu cuenta"));

        driver.quit();
    }
}
