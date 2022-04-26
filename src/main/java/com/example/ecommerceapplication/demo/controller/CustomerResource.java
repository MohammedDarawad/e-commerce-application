package com.example.ecommerceapplication.demo.controller;

import com.example.ecommerceapplication.demo.dto.CustomerDTO;
import com.example.ecommerceapplication.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/getCustomerById")
    public ResponseEntity<CustomerDTO> getCustomerById(@RequestParam(name = "id") int customerId) {
        return ResponseEntity.ok().body(customerService.getCustomerById(customerId));
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerDTO> customerLogin(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok().body(customerService.customerLogin(body.get("email").toString(), body.get("password").toString()));
    }

    @PostMapping("/signup")
    public ResponseEntity<CustomerDTO> customer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok().body(customerService.customerSignup(customerDTO));
    }
}
