package com.selenium.clase_7.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.clase_7.Pages.SearchPage;

public class BusquedaTest {
     private WebDriver driver;
     private WebDriverWait wait;

     @Tag("NIVEL_1")
     @Tag("REGRESION_BUSQUEDA")
     @Test
     public void testSearchExitoso() throws InterruptedException {
          driver = new ChromeDriver();
          SearchPage SearchPage = new SearchPage(driver, wait);

          SearchPage.setup();
          SearchPage.open("http://testing.ctd.academy/");

          SearchPage.escribirCiudad("punta del este");
          SearchPage.clickBuscar();

          assertTrue(SearchPage.obtenerResultadoRecomendacion().equals("CASA DE PLAYA\nVilla Kantounes Studio Ostria"));
          SearchPage.closeBrowser();
     }

     @Tag("REGRESION_BUSQUEDA")
     @Test
     public void testSearchFallido() throws InterruptedException {
          driver = new ChromeDriver();
          SearchPage SearchPage = new SearchPage(driver, wait);
          SearchPage.setup();
          SearchPage.open("http://testing.ctd.academy/");

          SearchPage.escribirCiudad("kjgfs");

          assertTrue(SearchPage.obtenerResultadoFallido().equals("No options"));
          SearchPage.closeBrowser();
     }
}