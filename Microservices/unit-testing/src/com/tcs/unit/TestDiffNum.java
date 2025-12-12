package com.tcs.unit;

//import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TestDiffNum {
	DiffNum dn = new DiffNum();

	@Test
	public void test1() {
		int diff = dn.getDiff(9876);

		int expected = 6174;
		assertEquals(diff, expected);
	}
    
    @Test
    public void test2() {
    	int diff = dn.getDiff(9436);

		int expected = 6174;
		assertEquals(diff, expected);
    }
    @Test
    public void test3() {
    	int diff = dn.getDiff(9876);


		int expected = 6174;
		assertEquals(diff, expected);
    }
    @Test
    public void test4() {
    	int diff = dn.getDiff(1111);


		int expected = 6174;
		assertNotEquals(diff, expected);
    }
    @Test
    public void test5() {
    	int diff = dn.getDiff(9176);

		int expected = 6174;
		assertEquals(diff, expected);
    }
}
