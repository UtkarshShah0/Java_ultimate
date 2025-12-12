package com.tcs.unit;

public class Compute {
	
	public double add(double a, double b) {
		
		return a + b;
	}
	
	public boolean prime(int a) {
		
		for (int i=2; i <= a/2; i++) {
			if (a%i == 0) {
				return false;
			}
		}
		return true;
	}
}
