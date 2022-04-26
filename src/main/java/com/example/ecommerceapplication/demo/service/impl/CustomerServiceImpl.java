package com.example.ecommerceapplication.demo.service.impl;

import com.example.ecommerceapplication.demo.dto.CustomerDTO;
import com.example.ecommerceapplication.demo.entity.CustomerEntity;
import com.example.ecommerceapplication.demo.exception.BadRequestException;
import com.example.ecommerceapplication.demo.exception.ResourceNotFoundException;
import com.example.ecommerceapplication.demo.repository.CustomerRepository;
import com.example.ecommerceapplication.demo.service.CustomerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapToDTO(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("CustomerEntity", "id", customerId));
        return mapToDTO(customer);
    }

    @Override
    public CustomerDTO customerLogin(String email, String password) {
        String hashedPassword = DigestUtils.sha256Hex(password);
        CustomerEntity customerEntity = customerRepository.findByEmailAndPassword(email, hashedPassword);
        return mapToDTO(customerEntity);
    }

    @Override
    public CustomerDTO customerSignup(CustomerDTO customerDTO) {
        if (!customerRepository.existsByEmail(customerDTO.getEmail())) {
            String hashedPassword = DigestUtils.sha256Hex(customerDTO.getPassword());
            customerDTO.setPassword(hashedPassword);
            CustomerEntity customerEntity = mapToEntity(customerDTO);
            return mapToDTO(customerRepository.save(customerEntity));
        }
        throw new BadRequestException("customer", "id already used");
    }

    private CustomerDTO mapToDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customerEntity.getCustomerId());
        customerDTO.setAddress(customerEntity.getAddress());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setFirstName(customerEntity.getFirstName());
        customerDTO.setLastName(customerEntity.getLastName());
        customerDTO.setPhoneNumber(customerEntity.getPhoneNumber());
        customerDTO.setPassword(customerEntity.getPassword());
        return customerDTO;
    }

    private CustomerEntity mapToEntity(CustomerDTO customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerDto.getCustomerId());
        customerEntity.setAddress(customerDto.getAddress());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setFirstName(customerDto.getFirstName());
        customerEntity.setLastName(customerDto.getLastName());
        customerEntity.setPhoneNumber(customerDto.getPhoneNumber());
        customerEntity.setPassword(customerDto.getPassword());
        return customerEntity;
    }
}
