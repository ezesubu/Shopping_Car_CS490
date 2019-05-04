package edu.mum.cs490.shoppingcart.service.impl;

import edu.mum.cs490.shoppingcart.domain.Admin;
import edu.mum.cs490.shoppingcart.domain.Status;
import edu.mum.cs490.shoppingcart.repository.AdminRepository;
import edu.mum.cs490.shoppingcart.service.IAdminService;
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
public class AdminServiceImpl extends UserServiceImpl<Admin> implements IAdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        super(adminRepository);
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> find(String username, String firstName, String lastName, Status status) {
        return adminRepository.find(username, firstName, lastName, status);
    }

    @Override
    public Page<Admin> findPage(String username, String firstName, String lastName, Status status, Pageable pageable) {
        return adminRepository.findPage(username, firstName, lastName, status, pageable);
    }
}
