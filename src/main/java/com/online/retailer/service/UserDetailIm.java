//package com.online.retailer.service;
//
//import com.online.retailer.model.Customer;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class UserDetailIm implements UserDetailsService {
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Customer customer = new Customer();
//        customer.setUser_id(1);
////        List<SimpleGrantedAuthority> collect = customer2.stream()
////                .map(customer -> new SimpleGrantedAuthority(customer.getAuthority()))
////                .collect(Collectors.toList());
//        return (UserDetails) customer;
//    }
//}
