package com.selenium.clase_7.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.selenium.clase_7.Pages.LoginPage;
import com.selenium.clase_7.Reportes.com.extentReports.ExtentFactory;

public class LoginTest {
     private WebDriver driver;
     private WebDriverWait wait;
     static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES.html");
     static ExtentReports extent;

     @BeforeAll
     public static void crearReporte() {
          extent = ExtentFactory.getInstance();
          extent.attachReporter(info);
     }

     @BeforeEach
     public void setUp() {
          driver = new ChromeDriver();
          wait = new WebDriverWait(driver, Duration.ofMillis(2000));
          LoginPage loginPage = new LoginPage(driver, wait);
          loginPage.setup();
          loginPage.open("http://testing.ctd.academy/");
     }

     @Test
     @Tag("NIVEL_1")
     public void LoginExitosoTest() throws InterruptedException {
          ExtentTest test = extent.createTest("Prueba de Login Exitoso");
          test.log(Status.INFO, "Comienza el Test");
          LoginPage loginPage = new LoginPage(driver, wait);

          loginPage.clickLogin();
          test.log(Status.PASS, "Ingresar a la pagina de Login");

          loginPage.escribirMail("prueba3@gmail.com");
          loginPage.escribirContrasena("123456");
          test.log(Status.PASS, "Completar los datos del Login");

          loginPage.clickIngresar();
          assertTrue(loginPage.obtenerUsuario().equals("Prueba3 Prueba3"));
          test.log(Status.PASS, "Validación de Ingreso Exitoso");
     }

     @Test
     public void LoginFallidoTest() throws InterruptedException {
          ExtentTest test = extent.createTest("Prueba de Login Fallida Mail Incorrecto");
          test.log(Status.INFO, "Comienza el Test");
          LoginPage loginPage = new LoginPage(driver, wait);

          loginPage.clickLogin();
          test.log(Status.PASS, "Ingresar a la pagina de Login");

          loginPage.escribirMail("prrrrr@gmail.com");
          loginPage.escribirContrasena("123456");
          test.log(Status.PASS, "Completar los datos erroneos de Mail");

          loginPage.clickIngresar();

          assertTrue(loginPage.obtenerMensajeError()
                    .equals("Sus credenciales son inválidas. Por favor, vuelva a intentarlo"));
          test.log(Status.PASS, "Validación de Credenciales inválidas");
     }

     @AfterEach
     public void cerrar() {
          LoginPage loginPage = new LoginPage(driver, wait);
          loginPage.closeBrowser();
     }

     @AfterAll
     public static void reporte() {
          extent.flush();
     }
}