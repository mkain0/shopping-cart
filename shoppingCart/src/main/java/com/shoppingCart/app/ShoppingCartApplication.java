package com.shoppingCart.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class ShoppingCartApplication {

    public static void main(String[] args) {
//    	 System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ShoppingCartApplication.class, args);
    }
}
