package edu.mum.cs490.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by Thomas Tibebu,
 * Date April 20, 2019
 **/
@Configuration
@PropertySource(value={"file:E:\\CS490-Project Management\\IntellijIDEA\\Shopping-Cart-CS490\\src\\main\\resources\\application.properties"})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/", "file:///" + env.getProperty("resource.dir"));
        registry.addResourceHandler("/static/**").addResourceLocations(env.getProperty("static.path"));
    }
}