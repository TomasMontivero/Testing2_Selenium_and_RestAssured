package com.selenium.opencart.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.selenium.opencart.extentReports.ExtentFactory;
import com.selenium.opencart.pages.CatalogPage;
import com.selenium.opencart.pages.HomePage;
import com.selenium.opencart.pages.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {

    // Pages
    HomePage homePage;
    RegisterPage registerPage;
    CatalogPage catalogPage;
    WebDriver driver;

    // Extent - Reporte en HTML
    ExtentTest test;
    static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
    static ExtentReports extent;


    @Test
    @Tag("smoke")
    public void registerAndPurchaseTest() {
        test = extent.createTest("Proceso de registro, búsqueda y adición del producto a la cesta.");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.goToHome();
        homePage.clickMyAccount();
        homePage.clickRegister();
        test.log(Status.PASS, "Ir al formulario de registro");
        // Register
        registerPage.inputFirstName("MiNombre");
        registerPage.inputLastName("MiApellido");
        registerPage.inputEmail( "email" + (int)(Math.random()*100000) + "@mail.com");
        registerPage.inputTelephone("123456789");
        registerPage.inputPassword("password");
        registerPage.inputPasswordConfirm("password");
        registerPage.clickNewsletterNo();
        registerPage.clickPrivacyPolicyAgree();
        registerPage.clickContinue();
        assertTrue(registerPage.getRegistrationConfirmation().equals("Congratulations! Your new account has been successfully created!"));
        test.log(Status.PASS, "Completado del formulario de registro");
        // Home
        homePage.goToHome();
        homePage.inputSearch("Iphone");
        homePage.clickSearch();
        test.log(Status.PASS, "Busqueda de Iphone");
        // Catalog
        catalogPage.clickAddToCart();
        assertTrue(catalogPage.getSuccessMessagge().contains("Success: You have added iPhone to your shopping cart!"));
        test.log(Status.PASS, "Producto agregado al carrito");
    }

    @BeforeAll
    public static void setupAll() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(spark);
    }

    @BeforeEach
    public void setupEach() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        catalogPage = new CatalogPage(driver);
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
