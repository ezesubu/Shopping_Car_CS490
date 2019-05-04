package edu.mum.cs490.shoppingcart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartApplicationTests {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Test
	public void contextLoads() {
		System.out.println(passwordEncoder.encode("12345"));
		System.out.println(passwordEncoder.matches("hello", "$2a$10$3kO/m80GNx5YGjmG2gFKEO3hi4aYZKaP1TIB.R5qKewq8Bn8R435i"));
	}

}
