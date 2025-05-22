package com.bajaj.ADID.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtils {
	public static void main(String[] args) {
		System.out.println("Is valid no : " + isValidNo("sgh"));
		System.out.println("Is valid no : " + isValidFloatingNo("989.25"));

		int c = 0;
		if (String.valueOf(c).equals("")) {
			System.out.println("hi");
		} else {
			System.out.println("bye");
		}
	}

//	^[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$
	public static boolean isValidFloatingNo(String s) {
		String regex = "[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		return (m.find() && m.group().equals(s));
	}

	public static boolean isValidNo(String s) {
		Pattern p = Pattern.compile("[+-]?[0-9][0-9]*");
		Matcher m = p.matcher(s);
		return (m.find() && m.group().equals(s));
	}

	public static boolean isNull(String str) {
		if (str.equals("") || str == null || str.equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}

	public static boolean isValidMobileNo(String s) {
		Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		Matcher m = p.matcher(s);
		return (m.find() && m.group().equals(s));
	}

	public static boolean isNullOrEmpty(String str[]) {
		boolean result = false;
		if (str == null || str.length == 0) {
			result = true;
		}
		return result;
	}

	public static boolean isNullOrEmpty(String str) {
		boolean result = false;
		if (str == null || str.trim().isEmpty()) {
			result = true;
		}
		return result;
	}

	public static boolean isNullOrEmpty(Object str) {
		boolean result = false;
		if (str == null) {
			result = true;
		}
		return result;
	}

	public static boolean isNullOrEmpty(List<?> list) {
		boolean result = false;
		if (list == null || list.isEmpty()) {
			result = true;
		}
		return result;
	}

	public static boolean isNull(Object obj) {
		boolean result = false;
		if (obj == null) {
			result = true;
		}
		return result;
	}

	public static boolean isZero(int obj) {
		boolean result = false;
		if (obj == 0) {
			result = true;
		}
		return result;
	}

	public static boolean isZero(long obj) {
		boolean result = false;
		if (obj == 0) {
			result = true;
		}
		return result;
	}
}
