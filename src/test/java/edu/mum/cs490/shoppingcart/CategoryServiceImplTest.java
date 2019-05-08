package edu.mum.cs490.shoppingcart;

import edu.mum.cs490.shoppingcart.domain.Category;
import edu.mum.cs490.shoppingcart.repository.CategoryRepository;
import edu.mum.cs490.shoppingcart.service.ICategoryService;
import edu.mum.cs490.shoppingcart.service.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Innocent Kateba on 5/06/2019
 */

@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {
    @TestConfiguration
    static class CategoryServiceImplTestContextConfiguration {

        @Bean
        public ICategoryService categoryService() {
            return new CategoryServiceImpl();
        }
    }

    @Autowired
    private ICategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        Category phone = new Category("phone");

        Mockito.when(categoryRepository.findByName(phone.getName()))
                .thenReturn(phone);
    }

    @Test
    public void whenValidName_thenCategoryShouldBeFound() {
        String name = "phone";
        Category found = categoryService.getCategoryByName(name);

        assertThat(found.getName())
                .isEqualTo(name);
    }
}
