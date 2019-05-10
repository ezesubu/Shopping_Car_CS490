/*
 * Created by: Ezesubu
 * Date: April 20, 2019
 * Name: View
 * DependingIn:  Boostrap3.6.->
 * Description: UI Template
 * Module: UI Template
 */

package edu.mum.cs490.shoppingcart.service.impl;


import edu.mum.cs490.shoppingcart.domain.Status;

import edu.mum.cs490.shoppingcart.domain.SuperAdmin;
import edu.mum.cs490.shoppingcart.repository.AdminRepository;
import edu.mum.cs490.shoppingcart.repository.SuperAdminRepository;
import edu.mum.cs490.shoppingcart.service.IAdminService;
import edu.mum.cs490.shoppingcart.service.ISuperAdminService;
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
public class SuperAdminServiceImpl extends UserServiceImpl<SuperAdmin> implements ISuperAdminService {

    private final SuperAdminRepository sadminRepository;

    @Autowired
    public SuperAdminServiceImpl(SuperAdminRepository sadminRepository) {
        super(sadminRepository);
        this.sadminRepository = sadminRepository;
    }

    @Override
    public List<SuperAdmin> find(String username, String firstName, String lastName, Status status) {
        return sadminRepository.find(username, firstName, lastName, status);
    }

    @Override
    public Page<SuperAdmin> findPage(String username, String firstName, String lastName, Status status, Pageable pageable) {
        return sadminRepository.findPage(username, firstName, lastName, status, pageable);
    }
}
