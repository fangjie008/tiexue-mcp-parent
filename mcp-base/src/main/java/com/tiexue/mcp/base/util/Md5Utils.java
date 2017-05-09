package com.tiexue.mcp.base.util;

import java.security.MessageDigest;

public class Md5Utils {

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static String toHexString(byte[] b) {
	    StringBuilder sb = new StringBuilder(b.length * 2);
	    for (int i = 0; i < b.length; i++) {
	        sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
	        sb.append(HEX_DIGITS[b[i] & 0x0f]);
	    }
	    return sb.toString();
	}

	public static String ToBit32(String SourceString,String key) throws Exception {
	    MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	    if(key!=null)
	    	SourceString=SourceString+key;
	    digest.update(SourceString.getBytes());
	    byte messageDigest[] = digest.digest();
	    return toHexString(messageDigest);
	}

	public static String ToBit16(String SourceString,String key) throws Exception {
	    return ToBit32(SourceString,key).substring(8, 24);
	}
}
