package com.restassured.samples;

import static io.restassured.RestAssured.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
//import org.testng.Assert;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getTest {
     @Test
     public void getReqRes() {
          given()
                    .when()
                    .get("https://reqres.in/api/users?page=2")
                    .then()
                    .statusCode(200)
                    .log().body();
     }

     @Test
     public void getReqRes_1() {
          Response response = RestAssured.get("https://reqres.in/api/users?page=2");
          System.out.println(response.getBody().asString());
          System.out.println(response.getStatusCode());
          System.out.println(response.getHeader("content-type"));
          System.out.println(response.getTime());
     }

     @Test
     public void getReqRes_2() {
          Response response = RestAssured.get("https://reqres.in/api/users?page=2");

          int statusCode = response.getStatusCode();
          JsonPath body = response.jsonPath();
          String mail = body.getString("data[0].email");
          String nombre = body.getString("data[0].first_name");

          Assert.assertEquals(statusCode, 200);
          Assert.assertEquals(mail, "michael.lawson@reqres.in");
          Assert.assertEquals(nombre, "Michael");

          System.out.println(statusCode);
          System.out.println(mail);
          System.out.println(response.getTime());
     }

     @Test
     public void getReqRes_3() {
          given()
                    .get("https://reqres.in/api/users?page=2")
                    .then()
                    .statusCode(200)
                    .body("data.id[0]", equalTo(7))
                    .body("data.email[0]", equalTo("michael.lawson@reqres.in"))
                    .log().status()
                    .log().body(false);
     }
}