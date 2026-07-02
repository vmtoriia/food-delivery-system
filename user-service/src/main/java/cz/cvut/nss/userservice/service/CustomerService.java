package cz.cvut.nss.userservice.service;

import cz.cvut.nss.userservice.dto.CreateCustomerRequest;
import cz.cvut.nss.userservice.entity.Customer;
import cz.cvut.nss.userservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer createCustomer(CreateCustomerRequest request) {
        if (customerRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Customer customer = Customer.builder()
                .email(request.email())
                .name(request.name())
                .role(request.role())
                .build();

        return customerRepository.save(customer);
    }
}