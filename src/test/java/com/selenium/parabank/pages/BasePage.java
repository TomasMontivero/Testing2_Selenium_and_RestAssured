package com.selenium.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebElement webElement(By by) {
        return driver.findElement(by);
    }

    public void get(String url) {
        driver.get(url);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.visibilityOf(webElement(locator)));
        driver.findElement(locator).click();
    }

    public void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOf(webElement(locator)));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        wait.until(ExpectedConditions.visibilityOf(webElement(locator)));
        return webElement(locator).getText();
    }

    public void waitTime() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        driver.navigate().refresh();
    }

}
