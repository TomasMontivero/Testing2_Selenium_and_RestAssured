package com.selenium.clase_7.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
     protected By logoImg = By.className("logo-img");
     protected By loginButtom = By.xpath("//a[text()='Iniciar sesión']");
     protected By registerButtom = By.xpath("//a[text()='Crear cuenta']");
     protected By nombreUsuario = By.className("txt-nombre");

     public WebDriver driver;
     public WebDriverWait wait;

     public BasePage(WebDriver driver, WebDriverWait wait) {
          this.driver = driver;
          this.wait = new WebDriverWait(driver, Duration.ofMillis(2000));
     }

     public void open(String url) {
          driver.get(url);
     }

     public void setup() {
          System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
          driver.manage().window().maximize();
     }

     public void closeBrowser() {
          driver.quit();
     }

     protected WebElement findElement(By locator) {
          wait.until(ExpectedConditions.presenceOfElementLocated(locator));
          return driver.findElement(locator);
     }

     protected void sendText(String imputText, By locator) {
          wait.until(ExpectedConditions.presenceOfElementLocated(locator));
          this.findElement(locator).clear();
          this.findElement(locator).sendKeys(imputText);
     }

     protected void sendKey(CharSequence pressKeys, By locator) {
          wait.until(ExpectedConditions.presenceOfElementLocated(locator));
          this.findElement(locator).sendKeys(pressKeys);
     }

     protected void clickear(By locator) {
          wait.until(ExpectedConditions.elementToBeClickable(locator));
          this.findElement(locator).click();
     }

     protected String getText(By locator) {
          wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
          return this.findElement(locator).getText();
     }

     public void clickLogo() throws InterruptedException {
          this.clickear(logoImg);
     }

     public void clickLogin() throws InterruptedException {
          this.clickear(loginButtom);
     }

     public void clickRegistrar() throws InterruptedException {
          this.clickear(registerButtom);
     }

     public String obtenerUsuario() {
          System.out.println("EL USUARIO ES: " + this.getText(nombreUsuario));
          return this.getText(nombreUsuario);
     }
}