package com.restassured.samples;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;


public class SampleTest {

    @Test
    public void pokeApiTest() {
        // Valida si la respuesta tiene status 200
        /*given().contentType("application/x-www-form-urlencoded; charset")
                .when().get("https://pokeapi.co/api/v2/pokemon/ditto")
                .then().statusCode(200);*/

        // Guarda el response del GET:
        Response response = given()
                .when().get("https://pokeapi.co/api/v2/pokemon/ditto");

        // Valida si el response tiene status 200
        response.then().assertThat().statusCode(200);

        // Explora el response y busca el path: "abilities.ability"
        ArrayList abilities = response.then().extract().path("abilities.ability");
        System.out.println(abilities);
    }

    @Test
    void test01_GET() {
        // Hago el GET y guardo el response
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Response: " + response.asString());
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("content-type: " + response.getHeader("content-type"));
        System.out.println("Response time: " + response.getTime());
        // Guardo en variables la info interna del response
        int statusCode = response.getStatusCode();
        String body = response.getBody().asString();
        // Validaciones
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals("michael.lawson@reqres.in", response.then().extract().path("data.email[0]"));
    }

    @Test
    void test02_GET() {
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200);
                //statusCode(200).body("data.id[0]", equalTo(7));
    }

    @Test
    void test02_GET_alternativa_guardando_el_response() {
        // Hago el GET y guardo el response
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        // Guardo en variables la info interna del response
        int statusCode = response.getStatusCode();
        String mail = response.jsonPath().getString("data[0].email");
        String nombre = response.jsonPath().getString("data[0].first_name");

        // Validaciones
        Assert.assertEquals("michael.lawson@reqres.in", mail);
        Assert.assertEquals("Michael", nombre);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void test03_POST() {
        JsonObject request = new JsonObject();
        request.addProperty("name", "Ajeet");
        request.addProperty("Job", "Teacher");
        given().
                header("Content-type","application/json").
                contentType(ContentType.JSON).
                body(request.toString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).log().all();
    }

    @Test
    void test03_POST_alternativa_guardando_el_response() {
        // Armo el body para el request
        JsonObject request = new JsonObject();
        request.addProperty("name", "Ajeet");
        request.addProperty("Job", "Teacher");

        // Hago el POST y guardo el response
        Response response =
                given().
                        header("Content-type","application/json").
                        contentType(ContentType.JSON).
                        body(request.toString()).
                        when().
                        post("https://reqres.in/api/users");

        // Guardo en variables la info interna del response
        //String nombre = response.jsonPath().getString("name");
        //String trabajo = response.jsonPath().getString("Job");
        JsonPath body = response.jsonPath();
        String nombre = body.getString("name");
        String trabajo = body.getString("Job");

        // Validaciones
        Assert.assertEquals( "Ajeet", nombre);
        Assert.assertEquals("Teacher", trabajo);
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Test
    void test04_PUT() {
        JsonObject request = new JsonObject();
        request.addProperty("name", "Amann");
        request.addProperty("Job", "Teacher");
        given().
                header("Content-type","application/json").
                contentType(ContentType.JSON).
                body(request.toString()).
                when().
                put("https://reqres.in/api/users/2").
                then().
                statusCode(200).log().all();
    }




}
