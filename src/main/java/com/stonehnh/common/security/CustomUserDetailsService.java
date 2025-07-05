package com.stonehnh.common.security;

import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerResponseDto customer = customerService.findCustomerByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Không tìm thấy user với email: " + email);
        }

        // Chuyển roleName -> List<SimpleGrantedAuthority>
        List<SimpleGrantedAuthority> authorities = customer.getRoleName().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // Trả về UserDetails
        return org.springframework.security.core.userdetails.User
                .withUsername(customer.getEmail())
                .password(customer.getPassword())
                .authorities(authorities)
                .accountLocked(!customer.getAccountStatus()) // khóa account nếu status=false
                .disabled(!customer.getVerifyStatus()) // vô hiệu hóa nếu chưa verify
                .build();
    }
}
