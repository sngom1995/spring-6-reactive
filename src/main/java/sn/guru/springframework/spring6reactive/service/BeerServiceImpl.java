package sn.guru.springframework.spring6reactive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
        return beerReactiveRepository.findAll().map(beerMapper::beerToBeerDTO);
    }
    @Override
    public Mono<BeerDTO> getBeerById(Integer id) {
        return beerReactiveRepository.findById(id).map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> saveBeer(BeerDTO beerDTO) {
        return beerReactiveRepository.save(beerMapper.BeerDTOToBeer(beerDTO)).map(beerMapper::beerToBeerDTO);
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
        }).flatMap(beerReactiveRepository::save).map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<Void> deleteBeerById(Integer id) {
        return beerReactiveRepository.deleteById(id);
    }

    @Override
    public Mono<BeerDTO> patchBeer(Integer beerId, BeerDTO beerDTO) {
        return beerReactiveRepository.findById(beerId)
                .map(foundBeer -> {
                    if(StringUtils.hasText(beerDTO.getBeerName())){
                        foundBeer.setBeerName(beerDTO.getBeerName());
                    }

                    if(StringUtils.hasText(beerDTO.getBeerStyle())){
                        foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    }

                    if(beerDTO.getPrice() != null){
                        foundBeer.setPrice(beerDTO.getPrice());
                    }

                    if(StringUtils.hasText(beerDTO.getUpc())){
                        foundBeer.setUpc(beerDTO.getUpc());
                    }

                    if(beerDTO.getQuantityOnHand() != null){
                        foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    }
                    return foundBeer;
                }).flatMap(beerReactiveRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

}
