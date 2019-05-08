package edu.mum.cs490.shoppingcart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Thomas Tibebu,
 * Date April 20, 2019
 **/
@Configuration
@Import({
        WebSecurityConfig.class,
        WebConfig.class,
        MockConnectionConfig.class
})
@ComponentScan({
        "edu.mum.cs490.shoppingcart.repository",
        "edu.mum.cs490.shoppingcart.controller",
        "edu.mum.cs490.shoppingcart.service",
        "edu.mum.cs490.shoppingcart.utility",

})
public class Configs {
}

