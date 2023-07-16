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
    public static final String BEER_PATH_ID = BEER_V1_PATH + "/{beerId}";


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
    Mono<ResponseEntity<Void>> updateBeer(@PathVariable Integer id, @RequestBody BeerDTO beerDTO) {
        beerService.updateBeer(id, beerDTO).subscribe();
        return Mono.just(ResponseEntity.ok().build());
    }

    @DeleteMapping(BEER_V1_PATH + "/{id}")
    Mono<Void> deleteBeerById(@PathVariable Integer id) {
        return beerService.deleteBeerById(id);
    }

    @PatchMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> patchExistingBeer(@PathVariable Integer beerId,
                                                 @RequestBody BeerDTO beerDTO){
        return beerService.patchBeer(beerId, beerDTO)
                .map(updatedDto -> ResponseEntity.ok().build());
    }
}
