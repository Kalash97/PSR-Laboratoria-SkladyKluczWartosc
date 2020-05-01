package com.Utils;

public class ValidUtil {

	public static boolean isIntInstance(String line) {
		try {
			Integer.parseInt(line);
		}catch (NumberFormatException e) {
			return false;
		}		
		return true;
	}
}
