package com.selenium.clase_7.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.selenium.clase_7.Pages.RegisterPage;
import com.selenium.clase_7.Reportes.com.extentReports.ExtentFactory;

public class RegisterTest {
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
          RegisterPage RegisterPage = new RegisterPage(driver, wait);
          RegisterPage.setup();
          RegisterPage.open("http://testing.ctd.academy/");
     }

     @Test
     public void RegistroExitosoTest() throws InterruptedException {
          ExtentTest test = extent.createTest("Prueba de Registro Exitoso");
          test.log(Status.INFO, "Comienza el Test");
          RegisterPage RegisterPage = new RegisterPage(driver, wait);

          RegisterPage.clickRegistrar();
          test.log(Status.PASS, "Ingresar a la pagina de Registro");

          RegisterPage.escribirNombre("Sergio");
          RegisterPage.escribirApellido("Pace");
          RegisterPage.escribirMail("prueba00XX@gmail.com");
          RegisterPage.escribirContrasena("123456");
          RegisterPage.repetirContrasena("123456");
          test.log(Status.PASS, "Completar los datos de Registro");

          RegisterPage.clickRegistrarse();

          assertTrue(RegisterPage.obtenerMensajeCuentaRegistrada().equals("¡Cuenta registrada con éxito!"));
          assertTrue(
                    RegisterPage.obtenerMensajeConfirmarMail().equals("Te enviamos un email para confirmar tu cuenta"));
          test.log(Status.PASS, "Validación cuenta creada exitosamente");
     }

     @Test
     public void RegistroFallidoNombreTest() throws InterruptedException {
          ExtentTest test = extent.createTest("Prueba de Registro Fallido Nombre obligatorio");
          test.log(Status.INFO, "Comienza el Test");
          RegisterPage RegisterPage = new RegisterPage(driver, wait);

          RegisterPage.clickRegistrar();
          test.log(Status.PASS, "Ingresar a la pagina de Registro");

          RegisterPage.escribirApellido("Pace");
          RegisterPage.escribirMail("prueba5469@gmail.com");
          RegisterPage.escribirContrasena("123456");
          RegisterPage.repetirContrasena("123456");
          test.log(Status.PASS, "Completar los datos de Registro");

          RegisterPage.clickRegistrarse();

          assertTrue(RegisterPage.obtenerMensajeNombreObligatorio().equals("Este campo es obligatorio"));
          test.log(Status.PASS, "Validación mensaje de Campo Nombre Obligatorio");
     }

     @Test
     public void RegistroFallidoMailTest() throws InterruptedException {
          ExtentTest test = extent.createTest("Prueba de Registro Fallido Mail obligatorio");
          test.log(Status.INFO, "Comienza el Test");
          RegisterPage RegisterPage = new RegisterPage(driver, wait);

          RegisterPage.clickRegistrar();
          test.log(Status.PASS, "Ingresar a la pagina de Registro");

          RegisterPage.escribirNombre("Sergio");
          RegisterPage.escribirApellido("Pace");
          RegisterPage.escribirContrasena("123456");
          RegisterPage.repetirContrasena("123456");
          test.log(Status.PASS, "Completar los datos de Registro");

          RegisterPage.clickRegistrarse();

          assertTrue(RegisterPage.obtenerMensajeNombreObligatorio().equals("Este campo es obligatorio"));
          test.log(Status.PASS, "Validación mensaje de Campo Mail Obligatorio");
     }

     @Test
     public void RegistroFallidoMailRegistrado() throws InterruptedException {
          ExtentTest test = extent.createTest("Prueba de Registro Fallido por Mail registrado");
          test.log(Status.INFO, "Comienza el Test");
          RegisterPage RegisterPage = new RegisterPage(driver, wait);

          RegisterPage.clickRegistrar();
          test.log(Status.PASS, "Ingresar a la pagina de Registro");

          RegisterPage.escribirNombre("Sergio");
          RegisterPage.escribirApellido("Pace");
          RegisterPage.escribirMail("prueba0015@gmail.com");
          RegisterPage.escribirContrasena("123456");
          RegisterPage.repetirContrasena("123456");
          test.log(Status.PASS, "Completar los datos de Registro");

          RegisterPage.clickRegistrarse();

          assertTrue(RegisterPage.obtenerMensajeMailRegistrado().equals("Ese email ya se encuentra registrado"));
          test.log(Status.PASS, "Validación mensaje de que ya se encuentra registrado el Mail");
     }

     @Test
     public void RegistroFallidoVacioTest() throws InterruptedException {
          ExtentTest test = extent.createTest("Prueba de Registro Fallido sin completar campos");
          test.log(Status.INFO, "Comienza el Test");
          RegisterPage RegisterPage = new RegisterPage(driver, wait);

          RegisterPage.clickRegistrar();
          test.log(Status.PASS, "Ingresar a la pagina de Registro");

          RegisterPage.clickRegistrarse();
          test.log(Status.PASS, "Presionamos Registrar sin completar ningún dato");

          assertTrue(RegisterPage.obtenerMensajeNombreObligatorio().equals("Este campo es obligatorio"));
          assertTrue(RegisterPage.obtenerMensajeApellidoObligatorio().equals("Este campo es obligatorio"));
          assertTrue(RegisterPage.obtenerMensajeEmailObligatorio().equals("Este campo es obligatorio"));
          assertTrue(RegisterPage.obtenerMensajeContraseñaObligatorio().equals("Este campo es obligatorio"));
          assertTrue(RegisterPage.obtenerMensajeConfirmaContraseñaObligatorio().equals("Este campo es obligatorio"));
          test.log(Status.PASS, "Validación todos los mensajes obligatorios");

     }

     @AfterEach
     public void cerrar() {
          RegisterPage RegisterPage = new RegisterPage(driver, wait);
          RegisterPage.closeBrowser();
     }

     @AfterAll
     public static void reporte() {
          extent.flush();
     }
}
