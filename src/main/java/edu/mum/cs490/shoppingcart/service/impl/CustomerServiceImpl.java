package edu.mum.cs490.shoppingcart.service.impl;

import edu.mum.cs490.shoppingcart.domain.Address;
import edu.mum.cs490.shoppingcart.domain.Customer;
import edu.mum.cs490.shoppingcart.domain.Status;
import edu.mum.cs490.shoppingcart.repository.AddressRepository;
import edu.mum.cs490.shoppingcart.repository.CustomerRepository;
import edu.mum.cs490.shoppingcart.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Service
public class CustomerServiceImpl extends UserServiceImpl<Customer> implements ICustomerService {

    private final CustomerRepository repository;
    private final AddressRepository addressRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository, AddressRepository addressRepository) {
        super(repository);
        this.repository = repository;
        this.addressRepository = addressRepository;
    }


    @Override
    public List<Address> findByUser_id(Integer userId) {
        return this.addressRepository.findByUser_idAndStatus(userId, Status.ENABLED);
    }

    @Override
    public Address findAddressById(Integer addressId) {
        return this.addressRepository.findById(addressId).orElse(null);
    }

    @Override
    public List<Customer> find(String username, String firstName, String lastName, Status status) {
        return repository.find(username, firstName, lastName, status);
    }

    @Override
    public Page<Customer> findPage(String username, String firstName, String lastName, Status status, Pageable pageable) {
        return repository.findPage(username, firstName, lastName, status, pageable);
    }
}
