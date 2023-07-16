package sn.guru.springframework.spring6reactive.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import sn.guru.springframework.spring6reactive.config.DatabaseConfig;
import sn.guru.springframework.spring6reactive.domain.Beer;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerReactiveRepositoryTest {
    
    @Autowired
    BeerReactiveRepository beerReactiveRepository;

    @Test
    void testCreateJson() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(getTestBeer()));
    }

    @Test
    void saveNewBeer() {
        beerReactiveRepository.save(getTestBeer())
                .subscribe(
                        beer -> {
                            System.out.println("Saved Beer: " + beer.toString());
                        }
                );

    }

    Beer getTestBeer() {
        return Beer.builder()
                .beerName("Guru")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(200)
                .upc("123456789")
                .build();
    }
}
