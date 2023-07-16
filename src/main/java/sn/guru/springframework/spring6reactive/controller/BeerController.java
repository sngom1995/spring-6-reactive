package sn.guru.springframework.spring6reactive.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import sn.guru.springframework.spring6reactive.model.BeerDTO;

import java.math.BigDecimal;

@RestController
public class BeerController {

    public static final String BEER_V1_PATH = "/api/v2/beer";


    @GetMapping(BEER_V1_PATH)
    Flux<BeerDTO> getBeers() {
        return Flux.just(BeerDTO.builder()
                .id(1)
                .beerName("Beer1")
                .beerStyle("PALE_ALE")
                .upc("123456789012")
                .price(BigDecimal.valueOf(12.95))
                .quantityOnHand(100)
                .build(),
                BeerDTO.builder().id(2).build(),
                BeerDTO.builder().id(3).build());
    }
}
