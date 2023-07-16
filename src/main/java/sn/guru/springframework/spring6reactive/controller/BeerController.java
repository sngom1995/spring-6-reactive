package sn.guru.springframework.spring6reactive.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sn.guru.springframework.spring6reactive.model.BeerDTO;
import sn.guru.springframework.spring6reactive.service.BeerService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String BEER_V1_PATH = "/api/v2/beer";

    private final BeerService beerService;

    @GetMapping(BEER_V1_PATH)
    Flux<BeerDTO> getBeers() {
        return beerService.listBeers();
    }

    @PostMapping(BEER_V1_PATH)
    Mono<ResponseEntity<Void>> saveBeer(@RequestBody BeerDTO beerDTO) {
        return beerService.saveBeer(beerDTO)
                .map(savedBeer -> ResponseEntity.created(
                        UriComponentsBuilder
                                .fromHttpUrl("http://localhost:8080/api/v2/beer/" + savedBeer.getId())
                .build()
                .toUri()).build());
    }

    @GetMapping(BEER_V1_PATH + "/{id}")
    Mono<BeerDTO> getBeerById(@PathVariable  Integer id) {
        return beerService.getBeerById(id);
    }

    @PutMapping(BEER_V1_PATH + "/{id}")
    Mono<BeerDTO> updateBeer(@PathVariable Integer id, @RequestBody BeerDTO beerDTO) {
        return beerService.updateBeer(id, beerDTO);
    }
}
