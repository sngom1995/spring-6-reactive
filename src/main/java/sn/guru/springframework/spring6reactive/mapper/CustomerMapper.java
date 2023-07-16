package sn.guru.springframework.spring6reactive.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import sn.guru.springframework.spring6reactive.domain.Customer;
import sn.guru.springframework.spring6reactive.model.CustomerDTO;


@Component
public class CustomerMapper {
   public Customer customerDtoToCustomer(CustomerDTO dto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }

    public CustomerDTO customerToCustomerDto(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);
        return dto;
    }
}
