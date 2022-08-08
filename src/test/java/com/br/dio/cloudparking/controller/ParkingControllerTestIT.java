package com.br.dio.cloudparking.controller;


import com.br.dio.cloudparking.controller.dto.ParkingDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

//Boa pratica colocar a RandomPort para integracao continua ou em ambientes de empresas
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTestIT {

    /*Foi adiciona a biblioteca RestAssured para trabalhar com os testes*/

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }
    @Test
    void create() {

        ParkingDTO createparkingDTO = new ParkingDTO();
        createparkingDTO.setColor("AMARELO");
        createparkingDTO.setLicense("MSS-1823");
        createparkingDTO.setState("BA");
        createparkingDTO.setModel("FIAT");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createparkingDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("MSS-1823"));
//                .body("color", Matchers.equalTo("AMARELO"));

    }



    @Test
    void WhenfindAllCheckResult() {

        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("license[0]", Matchers.equalTo("MSS-1823"));
                //para printar no console o corpo com a resposta
                //.extract().response().body().prettyPrint();
    }


}