package com.example.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorApplicationTests {

	@Test
	void contextLoads() {
		CalculatorApplication.main(new String[] {"test1", "test2"});
	}

}
