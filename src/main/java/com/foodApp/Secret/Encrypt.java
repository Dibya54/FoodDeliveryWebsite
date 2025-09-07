package com.foodApp.Secret;

public class Encrypt {
	public static String encrypt(String str) {
		 if (str == null) {
	            str = ""; // or handle as needed, e.g., return null or throw an exception
	        }
		String newStr = "";
		for(int i=0; i<=str.length()-1; i++) {
			newStr = newStr + ((char)(str.charAt(i)+secrept.getkey()));
		}
		return newStr;
	}
}
