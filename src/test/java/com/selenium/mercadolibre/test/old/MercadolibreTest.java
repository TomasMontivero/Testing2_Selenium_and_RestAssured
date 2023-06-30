package com.selenium.mercadolibre.test.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercadolibreTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mercadolibre.com.ar");
        driver.manage().window().maximize();

        WebElement searchBox = driver.findElement(By.id("cb1-edit"));
        WebElement searchButton = driver.findElement(By.className("nav-search-btn"));

        searchBox.sendKeys("iPhone 13");
        //searchBox.sendKeys(Keys.ENTER);
        searchButton.click();



        //WebElement memoryCapacityFilter = driver.findElement(By.linkText("256 a 511 GB"));
        WebElement memoryCapacityFilter = driver.findElement(By.linkText("iPhone 13 Pro Max"));
        memoryCapacityFilter.click();
        driver.quit();

    }
}
