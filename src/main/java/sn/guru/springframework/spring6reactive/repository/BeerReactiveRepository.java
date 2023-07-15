package sn.guru.springframework.spring6reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import sn.guru.springframework.spring6reactive.domain.Beer;

public interface BeerReactiveRepository extends ReactiveCrudRepository<Beer, Integer> {
}
