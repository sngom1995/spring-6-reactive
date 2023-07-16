package sn.guru.springframework.spring6reactive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sn.guru.springframework.spring6reactive.domain.Beer;
import sn.guru.springframework.spring6reactive.mapper.BeerMapper;
import sn.guru.springframework.spring6reactive.model.BeerDTO;
import sn.guru.springframework.spring6reactive.repository.BeerReactiveRepository;


@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerReactiveRepository beerReactiveRepository;
    private final BeerMapper beerMapper;
    @Override
    public Flux<BeerDTO> listBeers() {
        return beerReactiveRepository.findAll().map(beerMapper::BeerToBeerDTO);
    }
    @Override
    public Mono<BeerDTO> getBeerById(Integer id) {
        return beerReactiveRepository.findById(id).map(beerMapper::BeerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> saveBeer(BeerDTO beerDTO) {
        return beerReactiveRepository.save(beerMapper.BeerDTOToBeer(beerDTO)).map(beerMapper::BeerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> updateBeer(Integer id, BeerDTO beerDTO) {
        Mono<Beer> beerMono = beerReactiveRepository.findById(id);
        return beerMono.map(beer -> {
            beer.setBeerName(beerDTO.getBeerName());
            beer.setBeerStyle(beerDTO.getBeerStyle());
            beer.setQuantityOnHand(beerDTO.getQuantityOnHand());
            beer.setPrice(beerDTO.getPrice());
            return beer;
        }).flatMap(beerReactiveRepository::save).map(beerMapper::BeerToBeerDTO);
    }

}
