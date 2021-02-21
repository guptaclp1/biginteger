package com.wipro.biginteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigIntegerTest {

	@DisplayName("Test BigInteger ADD")
	@Test
	void testADD() {

		assertEquals("12", BigInteger.getValue("ADD", "7", "5"));
	}

	@DisplayName("Test BigInteger SUB")
	@Test
	void testSUB() {

		assertEquals("4", BigInteger.getValue("SUB", "-3", "-7"));
	}
	
	
	@DisplayName("Test BigInteger ADD")
	@Test
	void testADD1() {

		assertEquals("705", BigInteger.getValue("ADD", "700", "5"));
	}
	
	@DisplayName("Test BigInteger ADD")
	@Test
	void testADD2() {

		assertEquals("715", BigInteger.getValue("ADD", "700", "15"));
	}
	
	@DisplayName("Test BigInteger ADD")
	@Test
	void testADD3() {

		assertEquals("850", BigInteger.getValue("ADD", "700", "150"));
	}
	
	/*@DisplayName("Test BigInteger SUB")
	@Test
	void testSUB1() {

		assertEquals("695", BigInteger.getValue("SUB", "700", "5"));
	}
*/	
	@DisplayName("Test BigInteger SUB")
	@Test
	void testSUB2() {

		assertEquals("685", BigInteger.getValue("SUB", "700", "15"));
	}
	
	@DisplayName("Test BigInteger SUB")
	@Test
	void testSUB3() {

		assertEquals("550", BigInteger.getValue("SUB", "700", "150"));
	}


}
