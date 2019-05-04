package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.Product;
import edu.mum.cs490.shoppingcart.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/

@Transactional(readOnly = true)
public interface IProductService {

    Product getOne(Integer id);

    @Transactional
    @PreAuthorize("#product.vendor.id == principal.id or hasRole('ROLE_ADMIN')")
    Product saveOrUpdate(Product product);

    @Transactional
    @PreAuthorize("#hasAnyRole('ROLE_ADMIN' ,'ROLE_VENDOR')")
    void delete(Integer id);

    @Transactional
    @PreAuthorize("#hasAnyRole('ROLE_ADMIN' ,'ROLE_VENDOR')")
    void changeStatus(Integer id, Status status);

    @Transactional
    List<Product> find(String name, Integer categoryId, Integer vendorId, Status status, Sort sort);

    Page<Product> findPage(String name, Integer categoryId, Integer vendorId, Status status, Pageable pageable);
}
