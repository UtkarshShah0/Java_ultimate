package com.tcs.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestCompute2 {
	
	
	static Compute compute;
	
	@BeforeAll
	static public void before() {
		compute = new Compute();
	}
	
	
	@BeforeEach
	void beforeEach() {
		System.out.println("Before each test");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("After each test");
	}
	
	
	@AfterAll
	static public void after() {
		compute = null;
	}
	
	@ParameterizedTest(name="{index} - {0} is Prime")
	 @ValueSource(ints = {2,5,7,11,13} )
	void primetest(Integer nums) {
		
		boolean actual = compute.prime(nums);
		assertTrue(actual);
	}

	
	
	
	

	@Test
	void testAdd() {
//		fail("Not yet implemented");
		

		double a = 10;
		double b = 20;
		double expected = 30;
		double actual = compute.add(a, b);
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testAddTwoZeroes() {
//		fail("Not yet implemented");
		
		Compute compute = new Compute();
		double a = 0;
		double b = 0;
		double expected = 0;
		double actual = compute.add(a, b);
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddTwoNegatives() {
//		fail("Not yet implemented");
		
		Compute compute = new Compute();
		double a = -10;
		double b = -10;
		double expected = -20;
		double actual = compute.add(a, b);
		assertEquals(expected, actual);
	}

}
