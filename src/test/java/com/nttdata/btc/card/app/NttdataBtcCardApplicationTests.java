package com.nttdata.btc.card.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class NttdataBtcCardApplicationTests {

	@Test
	void contextLoads() {
		String expected = "btc-card";
		String actual = "btc-card";

		assertEquals(expected, actual);
	}

}
