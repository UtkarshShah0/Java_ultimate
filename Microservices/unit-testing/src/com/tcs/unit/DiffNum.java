package com.tcs.unit;


import java.util.Arrays;


public class DiffNum {

	public int getDiff(int input) {
		int res = 0;
		res = input;
//		while(res != 6174) {
		while(res != 6174) {  
			String str_1 =  Integer.toString(res);
		
			String num_1[]=str_1.split("");
			Arrays.sort(num_1);
			StringBuilder sb = new StringBuilder();
			for (String j: num_1) {
				sb.append(j);
			}
			
			int asc = Integer.parseInt(sb.toString());

			int desc= Integer.parseInt(sb.reverse().toString());

			res= desc-asc;
			
			if (res <= 999) {
				return 1;
			}
	
		}
		
		return res;
	
	}
	
	
	

}

