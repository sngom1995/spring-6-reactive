package sn.guru.springframework.spring6reactive.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import sn.guru.springframework.spring6reactive.domain.Customer;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>{
}
