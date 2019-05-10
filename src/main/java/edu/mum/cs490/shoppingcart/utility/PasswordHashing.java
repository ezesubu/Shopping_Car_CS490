package edu.mum.cs490.shoppingcart.utility;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHashing {

    public static void main(String[] args) {

        String[] originalPassword = {"admin", "vendor", "user","customer","super"};
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = "";

        System.out.println("ORIGINAL \t HASHED");
        System.out.println("=========\t=======");
        for(String password : originalPassword){
            hashedPassword = encoder.encode(password);
            System.out.println(password + "\t\t" + hashedPassword);
        }
    }
}
