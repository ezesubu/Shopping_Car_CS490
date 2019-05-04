package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.Admin;
import edu.mum.cs490.shoppingcart.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public interface IAdminService extends IUserService<Admin> {

    List<Admin> find(String username, String firstName, String lastName, Status status);

    Page<Admin> findPage(String username, String firstName, String lastName, Status status, Pageable pageable);
}
