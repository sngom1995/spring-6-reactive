package sn.guru.springframework.spring6reactive.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.guru.springframework.spring6reactive.domain.Beer;
import sn.guru.springframework.spring6reactive.repository.BeerReactiveRepository;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class BootsTrapData implements CommandLineRunner {
    private final BeerReactiveRepository beerReactiveRepository;
    @Override
    public void run(String... args) throws Exception {
        loadBeer();
        beerReactiveRepository.count().subscribe( count -> {
            System.out.println("Loaded Beers: " + count);
        });

    }

    private  void loadBeer() throws Exception {
        beerReactiveRepository.count().subscribe( count -> {
            if(count == 0) {
                Beer beer = Beer.builder()
                        .beerName("Guru")
                        .beerStyle("IPA")
                        .price(BigDecimal.TEN)
                        .quantityOnHand(200)
                        .upc("123456789")
                        .build();

                Beer beer2 = Beer.builder()
                        .beerName("Guru2")
                        .beerStyle("IPA")
                        .price(BigDecimal.TEN)
                        .quantityOnHand(200)
                        .upc("123456789")
                        .build();

                Beer beer3 = Beer.builder()
                        .beerName("Guru3")
                        .beerStyle("IPA")
                        .price(BigDecimal.TEN)
                        .quantityOnHand(200)
                        .upc("123456789")
                        .build();

                beerReactiveRepository.save(beer).subscribe( beer1 -> {
                    System.out.println("Beer 1 saved");
                });
                beerReactiveRepository.save(beer2).subscribe( beer1 -> {
                    System.out.println("Beer 2 saved");
                });
                beerReactiveRepository.save(beer3).subscribe( beer1 -> {
                    System.out.println("Beer 3 saved");
                });
            }
        });
    }
}

