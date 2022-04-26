package com.example.ecommerceapplication.demo.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private int customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String phoneNumber;

    private String password;
}
