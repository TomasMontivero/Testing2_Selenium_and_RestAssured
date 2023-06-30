package com.selenium.clase_7.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {
     private By searchBox = By.id("ciudad");
     private By searchButton = By.id("btn-buscador");
     private By searchResult = By.className("categoria");
     private By searchResultFail = By.id("react-select-2-listbox");

     public SearchPage(WebDriver driver, WebDriverWait wait) {
          super(driver, null);
     }

     public void escribirCiudad(String ciudad) throws InterruptedException {
          this.sendText(ciudad, searchBox);
          this.sendKey(Keys.ENTER, searchBox);
          Thread.sleep(1000);
     }

     public void clickBuscar() throws InterruptedException {
          this.clickear(searchButton);
          Thread.sleep(1000);
     }

     public String obtenerResultadoRecomendacion() {
          String res = this.getText(searchResult);
          System.out.println("Resultado Card value: " + res);
          return res;
     }

     public String obtenerResultadoFallido() {
          String res = this.getText(searchResultFail);
          System.out.println("Resultado Card value: " + res);
          return res;
     }
}
