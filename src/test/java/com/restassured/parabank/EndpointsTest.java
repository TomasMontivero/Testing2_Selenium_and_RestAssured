package com.restassured.parabank;

import io.restassured.response.Response;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndpointsTest {


    // Validación del código de estado 200 para todas las etapas de las pruebas frontales
    // Documentacion APIs: https://parabank.parasoft.com/parabank/api-docs/index.html



    // ---------------------------------------------------------------------------------------------------
    // Registro URL:
    // https://parabank.parasoft.com/parabank/register.html
    // No logré hacer que este test funcione
    @Test
    @Order(1)
    public void registerTest() {
        String randomUsername = "username" + (int)(Math.random()*100000);
        String URL = "https://parabank.parasoft.com/parabank/register.htm?customer.firstName=Name&customer.lastName=LastName&customer.address.street=Address&customer.address.city=City&customer.address.state=State&customer.address.zipCode=123&customer.phoneNumber=12345678&customer.ssn=1234567890&customer.username=" + randomUsername + "&customer.password=password&repeatedPassword=password";
        Response response = given()
                .when().post(URL);
        Assert.assertEquals(200, response.getStatusCode());

        //String URL = "https://parabank.parasoft.com/parabank/register.htm";
        // String randomUsername = "username" + (int)(Math.random()*100000);
        //JsonObject request = new JsonObject();
        //request.addProperty("customer.firstName", "Name");
        //request.addProperty("customer.lastName", "LastName");
        //request.addProperty("customer.address.street", "Address");
        //request.addProperty("customer.address.city", "City");
        //request.addProperty("customer.address.state", "State");
        //request.addProperty("customer.address.zipCode", "123");
        //request.addProperty("customer.phoneNumber", "12345678");
        //request.addProperty("customer.ssn", "1234567890");
        //request.addProperty("customer.username", randomUsername);
        //request.addProperty("customer.password", "password");
        //request.addProperty("repeatedPassword", "password");
        //Response response = given()
        //        .header("Content-type","application/json")
        //        .body(request.toString())
        //        .when().post(URL);
        //Assert.assertEquals(200, response.getStatusCode());
    }


    // ---------------------------------------------------------------------------------------------------
    // Abrir una nueva cuenta URL:
    // https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?customerId=12545&newAccountType=1&fromAccountId=xxxxx
    @Test
    @Order(2)
    public void newAccountTest() {
        String URL = "https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId=12212&newAccountType=1&fromAccountId=12345";
        Response response = given()
                .auth().basic("john", "demo")
                .when().post(URL);

        Assert.assertEquals("12212", response.body().xmlPath().getString("account.customerId"));
        Assert.assertEquals("SAVINGS", response.body().xmlPath().getString("account.type"));
        Assert.assertEquals(200, response.getStatusCode());
    }


    // ---------------------------------------------------------------------------------------------------
    // Resumen de las cuentas URL:
    // https://parabank.parasoft.com/parabank/overview.htm
    @Test
    @Order(3)
    public void accountOverviewTest() {
        String URL = "https://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts";
        Response response = given()
                .auth().basic("john", "demo")
                .when().get(URL);
        Assert.assertEquals("12212", response.body().xmlPath().getString("accounts.account[0].customerId"));
        Assert.assertEquals("CHECKING", response.body().xmlPath().getString("accounts.account[0].type"));
        Assert.assertEquals(200, response.getStatusCode());
    }


    // ---------------------------------------------------------------------------------------------------
    // Descarga de fondos URL:
    //  https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx
    @Test
    @Order(4)
    public void transferFundsTest() {
        String URL = "https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=12345&toAccountId=12456&amount=100";
        Response response = given()
                .auth().basic("john", "demo")
                .when().post(URL);
        Assert.assertEquals("Successfully transferred $100 from account #12345 to account #12456", response.getBody().prettyPrint());
        Assert.assertEquals(200, response.getStatusCode());
    }


    // ---------------------------------------------------------------------------------------------------
    // Actividad de la cuenta (cada mes) URL:
    // https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13566/transactions/month/All/type/All
    @Test
    @Order(5)
    public void accountActivityTest() {
        String URL = "https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/12345/transactions/month/All/type/All";
        Response response = given()
                .auth().basic("john", "demo")
                .when().get(URL);
        Assert.assertEquals("12345", response.body().jsonPath().getString("accountId[0]"));
        Assert.assertEquals(200, response.getStatusCode());
    }

}
