package com.example.ecommerceapplication.demo.service;

import com.example.ecommerceapplication.demo.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers ();

    CustomerDTO getCustomerById (int customerId);

    CustomerDTO customerLogin (String email, String password);

    CustomerDTO customerSignup (CustomerDTO customerDTO);

    CustomerDTO updateCustomer (CustomerDTO customerDTO, int customerId);

    void deleteCustomerById(int customerId);

}
