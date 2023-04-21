package com.example.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CalculatorApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		CalculatorApplication.main(new String[] {"test1"});
		assertThat(context).isNotNull();
	}

}
