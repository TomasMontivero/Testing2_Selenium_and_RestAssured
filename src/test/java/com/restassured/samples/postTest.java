package com.restassured.samples;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonObject;

public class postTest {
     @Test
     public void postReqRes() {
          JsonObject request = new JsonObject();
          request.addProperty("name", "space");
          request.addProperty("job", "leader");

          given()
                    .contentType("application/json")
                    .body(request.toString())
                    .when()
                    .post("https://reqres.in/api/users")
                    .then()
                    .statusCode(201)
                    .log().status()
                    .log().body();
     }

     @Test
     public void postReqResFallido() {
          JsonObject request = new JsonObject();
          request.addProperty("email", "space@space");

          given()
                    .body(request.toString())
                    .when()
                    .post("https://reqres.in/api/login")
                    .then()
                    .statusCode(400)
                    .log().status()
                    .log().body();
     }
}