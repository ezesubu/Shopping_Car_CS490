package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.Category;
import edu.mum.cs490.shoppingcart.domain.Status;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Transactional(readOnly = true)
public interface ICategoryService {

    Category getCategoryById(Integer categoryId);

    Category getCategoryByName(String name);

    List<Category> find(String name, Integer parentId, Status status);


    List<Category> findAllActiveInList();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    void save(Category category);

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Integer categoryId);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    void changeStatus(Integer id, Status status);



}
