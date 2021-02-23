package com.wky.util;
import org.apache.shiro.crypto.hash.Md5Hash;

/*
 *123456 =ba61ce8fa1e3725876e6363c76043c8d
 */
//使用md算法加密
public class CryptographyUtil {
	
	public static void main(String[] args) throws Exception {
		System.out.println(md5("111111", "java"));
	}

	public static String md5(String pwd, String salt) {
		return new Md5Hash(pwd, salt).toString();
	}


}
