package sn.guru.springframework.spring6reactive.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import sn.guru.springframework.spring6reactive.domain.Beer;
import sn.guru.springframework.spring6reactive.model.BeerDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class BeerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(2)
    void testListBeers() {
        webTestClient.get().uri(BeerController.BEER_V1_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    @Order(1)
    void testGetBeerById() {

        webTestClient.get().uri(BeerController.BEER_V1_PATH.concat("/{id}"), 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1);
    }

    @Test
    @Order(3)
    void testSaveBeer() {
        webTestClient.post().uri(BeerController.BEER_V1_PATH)
                .body(Mono.just(getTestBeer()), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v2/beer/4");
    }

    @Test
    @Order(4)
    void testUpdateBeer() {
        BeerDTO beerDTO = getTestBeer();
        beerDTO.setPrice(BigDecimal.valueOf(12.99));

        webTestClient.put().uri(BeerController.BEER_V1_PATH.concat("/{id}"), 1)
                .body(Mono.just(beerDTO), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(999)
    void testDeleteBeer() {
        webTestClient.delete().uri(BeerController.BEER_V1_PATH.concat("/{id}"), 1)
                .exchange()
                .expectStatus().isOk();

    }

    BeerDTO getTestBeer() {
        return BeerDTO.builder()
                .beerName("Guru")
                .beerStyle("IPA")
                .price(BigDecimal.valueOf(123.45))
                .quantityOnHand(200)
                .upc("123456789")
                .build();
    }
}
