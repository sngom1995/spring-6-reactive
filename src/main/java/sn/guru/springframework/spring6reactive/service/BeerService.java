package sn.guru.springframework.spring6reactive.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sn.guru.springframework.spring6reactive.domain.Beer;
import sn.guru.springframework.spring6reactive.model.BeerDTO;

public interface BeerService {

    Flux<BeerDTO> listBeers() ;

    Mono<BeerDTO> getBeerById(Integer id);

    Mono<BeerDTO> saveBeer(BeerDTO beerDTO);

    Mono<BeerDTO> updateBeer(Integer id, BeerDTO beerDTO);

    Mono<Void> deleteBeerById(Integer id);

    Mono<BeerDTO> patchBeer(Integer id, BeerDTO beerDTO);

}
