package com.selenium.mercadolibre.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.selenium.mercadolibre.extentReports.ExtentFactory;
import com.selenium.mercadolibre.pages.CatalogPage;
import com.selenium.mercadolibre.pages.FilterPage;
import com.selenium.mercadolibre.pages.LoginPage;
import com.selenium.mercadolibre.pages.SearchPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BusquedaTest {

    // Pages
    WebDriver driver;
    private static SearchPage searchPage;
    private static FilterPage filterPage;
    private static CatalogPage catalogPage;
    private static LoginPage loginPage;
    // ExtentTest (reportes en HTML)
    ExtentTest test;
    static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
    static ExtentReports extent;


    @Test
    @Tag("smoke")
    public void testSearchAndFilterByMemory() {
        test = extent.createTest("Test Search And Filter By Memory");
        test.log(Status.INFO, "Inicia el test...");
        // Busqueda
        test.log(Status.PASS, "SearchPage");
        searchPage.get("https://www.mercadolibre.com.ar");
        searchPage.search("iPhone");
        searchPage.click();
        // Filtros
        test.log(Status.PASS, "FilterPage");
        filterPage.filterByMemory();
        // Comprar
        test.log(Status.PASS, "CatalogPage");
        catalogPage.selectProduct();
        catalogPage.buy();
        // Login
        assertTrue(loginPage.getLoginMessage().equals("¡Hola! Para comprar, ingresá a tu cuenta"));
    }

    @Test
    @Tag("smoke")
    @Tag("regression")
    public void testRepetido() {
        test = extent.createTest("Test repetido");
        test.log(Status.INFO, "Inicia el test repetido...");
        // Busqueda
        searchPage.get("https://www.mercadolibre.com.ar");
        searchPage.search("iPhone");
        searchPage.click();
        // Filtros
        filterPage.filterByMemory();
        // Comprar
        catalogPage.selectProduct();
        catalogPage.buy();
        // Login
        assertTrue(loginPage.getLoginMessage().equals("¡Hola! Para comprar, ingresá a tu cuenta"));
    }

    @BeforeAll
    public static void setupAll() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(spark);
    }

    @BeforeEach
    public void setupEach() {
        driver = new ChromeDriver();
        searchPage = new SearchPage(driver);
        filterPage = new FilterPage(driver);
        catalogPage = new CatalogPage(driver);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
    }

    @AfterEach
    public void finishEach() {
        driver.quit();
    }

    @AfterAll
    public static void finishAll() {
        extent.flush();
    }


}
