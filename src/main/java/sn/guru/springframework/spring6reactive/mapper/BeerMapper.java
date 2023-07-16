package sn.guru.springframework.spring6reactive.mapper;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import sn.guru.springframework.spring6reactive.domain.Beer;
import sn.guru.springframework.spring6reactive.model.BeerDTO;

@Component
public class BeerMapper {

    public BeerDTO beerToBeerDTO(Beer beer) {
        BeerDTO beerDTO = new BeerDTO();
        BeanUtils.copyProperties(beer, beerDTO);
        return beerDTO;
    }

    public Beer BeerDTOToBeer(BeerDTO beerDTO) {
        Beer beer = new Beer();
        BeanUtils.copyProperties(beerDTO, beer);
        return beer;
    }
}
