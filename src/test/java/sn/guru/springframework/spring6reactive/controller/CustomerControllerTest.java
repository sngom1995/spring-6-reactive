package sn.guru.springframework.spring6reactive.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import sn.guru.springframework.spring6reactive.model.CustomerDTO;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(999)
    void deleteById() {
        webTestClient.delete().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(3)
    void updateExistingCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder().customerName("Guru").build();
        webTestClient.put().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .bodyValue(customerDTO)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(2)
    void createNewCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder().customerName("samba").build();
        webTestClient.post().uri(CustomerController.CUSTOMER_PATH)
                .bodyValue(customerDTO)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @Order(1)
    void getCustomerById() {
        webTestClient.get().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1);
    }

    @Test
    void listCustomers() {
        webTestClient.get().uri(CustomerController.CUSTOMER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(3);
    }
}
