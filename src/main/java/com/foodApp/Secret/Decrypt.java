package com.foodApp.Secret;

public class Decrypt {
	public static String decrypt(String str) {
		String newStr = "";
		for(int i=0; i<=str.length()-1; i++) {
			newStr = newStr + ((char)(str.charAt(i)-secrept.getkey()));
		}
		return newStr;
	}
}
