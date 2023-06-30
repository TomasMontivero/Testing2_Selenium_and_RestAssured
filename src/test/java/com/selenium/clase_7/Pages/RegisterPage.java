package com.selenium.clase_7.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
     private By nombreId = By.id("firstName");
     private By apellidoId = By.id("lastName");
     private By mailId = By.id("email");
     private By passwordId = By.id("password");
     private By repasswordId = By.id("repassword");
     private By obligatorio1 = By.xpath("(//small[contains(text(),'Este campo es obligatorio')])[1]");
     private By obligatorio2 = By.xpath("(//small[contains(text(),'Este campo es obligatorio')])[2]");
     private By obligatorio3 = By.xpath("(//small[contains(text(),'Este campo es obligatorio')])[3]");
     private By obligatorio4 = By.xpath("(//small[contains(text(),'Este campo es obligatorio')])[4]");
     private By obligatorio5 = By.xpath("(//small[contains(text(),'Este campo es obligatorio')])[5]");
     private By registrarButtomClass = By.xpath("//button[normalize-space()='Registrarse']");
     private By mailRegistradoClass = By.className("form-feedback");
     private By finalizoClass = By.className("txt-gracias");
     private By graciasClass = By.className("txt-exito");

     public RegisterPage(WebDriver driver, WebDriverWait wait) {
          super(driver, null);
     }

     public void escribirNombre(String mail) throws InterruptedException {
          sendText(mail, nombreId);
     }

     public void escribirApellido(String mail) throws InterruptedException {
          sendText(mail, apellidoId);
     }

     public void escribirMail(String clave) throws InterruptedException {
          sendText(clave, mailId);
     }

     public void escribirContrasena(String clave) throws InterruptedException {
          sendText(clave, passwordId);
     }

     public void repetirContrasena(String clave) throws InterruptedException {
          sendText(clave, repasswordId);
     }

     public void clickRegistrarse() throws InterruptedException {
          clickear(registrarButtomClass);
     }

     public String obtenerMensajeNombreObligatorio() {
          System.out.println("MENSAJE NOMBRE OBLIGATORIO: " + this.getText(obligatorio1));
          return this.getText(obligatorio1);
     }

     public String obtenerMensajeApellidoObligatorio() {
          System.out.println("MENSAJE: " + this.getText(obligatorio2));
          return this.getText(obligatorio2);
     }

     public String obtenerMensajeEmailObligatorio() {
          System.out.println("MENSAJE: " + this.getText(obligatorio3));
          return this.getText(obligatorio3);
     }

     public String obtenerMensajeContraseñaObligatorio() {
          System.out.println("MENSAJE: " + this.getText(obligatorio4));
          return this.getText(obligatorio4);
     }

     public String obtenerMensajeConfirmaContraseñaObligatorio() {
          System.out.println("MENSAJE: " + this.getText(obligatorio5));
          return this.getText(obligatorio5);
     }

     public String obtenerMensajeMailRegistrado() {
          System.out.println("MENSAJE: " + this.getText(mailRegistradoClass));
          return this.getText(mailRegistradoClass);
     }

     public String obtenerMensajeCuentaRegistrada() {
          System.out.println("MENSAJE: " + this.getText(finalizoClass));
          return this.getText(finalizoClass);
     }

     public String obtenerMensajeConfirmarMail() {
          System.out.println("MENSAJE: " + this.getText(graciasClass));
          return this.getText(graciasClass);
     }
}