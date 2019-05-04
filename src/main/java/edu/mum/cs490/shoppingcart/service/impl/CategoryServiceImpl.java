package edu.mum.cs490.shoppingcart.service.impl;

import edu.mum.cs490.shoppingcart.domain.Category;
import edu.mum.cs490.shoppingcart.domain.Status;
import edu.mum.cs490.shoppingcart.repository.CategoryRepository;
import edu.mum.cs490.shoppingcart.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Service

public class CategoryServiceImpl implements ICategoryService {


    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> find(String name, Integer parentId, Status status) {
        return categoryRepository.find(name, parentId, status);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }


    @Override
    public List<Category> findAllActiveInList() {
        return categoryRepository.findByStatusOrderByParentCategoryAsc(Status.ENABLED);
    }


    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }



    @Override
    public void delete(Integer categoryId) {
        Category category = getCategoryById(categoryId);
        category.setStatus(Status.DELETED);
        categoryRepository.save(category);
    }

    @Override
    public void changeStatus(Integer id, Status status) {
        Category category = getCategoryById(id);
        category.setStatus(status);
        categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.getOne(categoryId);
    }



}
