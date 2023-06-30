package com.selenium.parabank.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.selenium.opencart.extentReports.ExtentFactory;
import com.selenium.parabank.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerTest {

    // ----------------------------------------------------------------------------
    // Pages
    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    OverviewPage overviewPage;
    OpenAccountPage openAccountPage;
    TransferFundsPage transferFundsPage;

    // Extent - Reporte en HTML
    ExtentTest test;
    static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
    static ExtentReports extent;


    // ----------------------------------------------------------------------------
    // Tests

    @Test
    @Order(1)
    @Tag("smoke")
    public void registerRandomUserTest() {
        test = extent.createTest("Proceso de registro de usuario random");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.goToRegister();
        test.log(Status.PASS, "Ir al formulario de registro");
        // Register
        registerPage.fillRegisterFormRandomUser();
        registerPage.submitForm();
        test.log(Status.PASS, "Completar el formulario de registro");
        assertTrue(registerPage.getSuccessMessage().equals("Your account was created successfully. You are now logged in."));
        test.log(Status.PASS, "Registro exitoso");
    }

    @Test
    @Order(2)
    @Tag("smoke")
    // Si el usuario (username/password) existe en la base de datos, este test debe fallar
    // Si resetearon la base de datos, este test es exitoso y permite a los siguientes test usar este usuario fijo
    public void registerFixedUserTest() {
        test = extent.createTest("Proceso de registro de usuario fijo para los siguientes tests");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.goToRegister();
        test.log(Status.PASS, "Ir al formulario de registro");
        // Register
        registerPage.fillRegisterFormFixedUser();
        registerPage.submitForm();
        test.log(Status.PASS, "Completar el formulario de registro");
        assertTrue(registerPage.getSuccessMessage().equals("Your account was created successfully. You are now logged in."));
        test.log(Status.PASS, "Registro exitoso");
    }

    @Test
    @Order(3)
    @Tag("regression")
    public void newAccountTest() {
        test = extent.createTest("Apertura de una nueva cuenta");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.login();
        test.log(Status.INFO, "Login exitoso");
        // Overview
        overviewPage.goToNewAccount();
        // New account
        openAccountPage.selectSavings();
        openAccountPage.submitNewAccount();
        assertTrue(openAccountPage.getSuccessMessage().equals("Congratulations, your account is now open."));
        test.log(Status.PASS, "Apertura de nueva cuenta exitosa");
    }

    @Test
    @Order(4)
    @Tag("regression")
    public void accountOverviewTest() {
        test = extent.createTest("Resumen de las cuentas");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.login();
        test.log(Status.INFO, "Login exitoso");
        // Overview
        overviewPage.goToAccountsOverview();
        assertTrue(overviewPage.getDisclaimer().contains("*Balance includes deposits that may be subject to holds"));
        test.log(Status.INFO, "Mensaje visible confirmado");
    }

    @Test
    @Order(5)
    @Tag("regression")
    public void transferFundsTest() {
        test = extent.createTest("Transferir Fondos");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.login();
        test.log(Status.INFO, "Login exitoso");
        // Overview
        overviewPage.goToTransferFunds();
        // Transfer funds
        assertTrue(transferFundsPage.getTransferFundsText().equals("Transfer Funds"));
        transferFundsPage.validateMenuOption();
        transferFundsPage.fillAmmount();
        transferFundsPage.selectFromAccount();
        transferFundsPage.selectToAccount();
        transferFundsPage.submitTransfer();
        assertTrue(transferFundsPage.getTransferSuccessMessage().equals("Transfer Complete!"));
        test.log(Status.INFO, "Transferencia exitosa");
    }

    @Test
    @Order(6)
    @Tag("regression")
    public void accountActivityTest() {
        test = extent.createTest("Actividad de la cuenta (cada mes)");
        test.log(Status.INFO, "Inicia el test...");
        // Home
        homePage.login();
        test.log(Status.INFO, "Login exitoso");
        // Overview
        overviewPage.goToAccountsOverview();
        assertTrue(overviewPage.getDisclaimer().contains("*Balance includes deposits that may be subject to holds"));
        overviewPage.selectAccount();
        overviewPage.getAccountDetailsText();
        overviewPage.selectActivityPeriod();
        overviewPage.selectTransactionType();
        overviewPage.submit();
        test.log(Status.INFO, "Revision de actividad de cuenta exitosa");
    }



    // ----------------------------------------------------------------------------
    // Before & After

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
        overviewPage = new OverviewPage(driver);
        openAccountPage = new OpenAccountPage(driver);
        transferFundsPage = new TransferFundsPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        homePage.goToHome();
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
