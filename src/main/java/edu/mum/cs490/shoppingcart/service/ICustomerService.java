package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.Address;
import edu.mum.cs490.shoppingcart.domain.Customer;
import edu.mum.cs490.shoppingcart.domain.Status;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public interface ICustomerService extends IUserService<Customer> {

    public List<Customer> find(String username, String firstName, String lastName, Status status);

    public Page<Customer> findPage(String username, String firstName, String lastName, Status status, Pageable pageable);

    public List<Address> findByUser_id(Integer userId);

    public Address findAddressById(Integer addressId);

}
