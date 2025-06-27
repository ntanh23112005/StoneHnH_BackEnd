package com.stonehnh.customer.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreationCustomerWithRoles {
    private CreationCustomerDto creationCustomer;
    private List<String> roleIds;
}
