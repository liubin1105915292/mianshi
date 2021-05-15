package com.luki.util;

import cn.hutool.core.codec.Base64;
import com.sun.xml.internal.ws.api.model.CheckedException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Base64Utils {

	// 密钥
	private static final String KEY = "renew-pc:renew-pc";

	public static void main(String[] args) throws Exception {
		String encoded = encode(KEY);
		String decoded = decode(encoded);
		System.out.println("==encode==="+encoded);
		System.out.println("==decode==="+decoded);
	}

	/**
	 * 加密
	 * @param text 明文
	 * @return     密文
	 */
	public static String encode(String text) throws Exception {
		byte[] encode;
		try {
			encode= text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new Exception( "Failed to Base64 encode");
		}
		return Base64.encode(encode);
	}

	/**
	 * 解密
	 * @param ciphertext 密文
	 * @return           明文
	 */
	public static String decode(String ciphertext) throws Exception {
		byte[] decoded;
		try {
			byte[] base64Token = ciphertext.getBytes("UTF-8");
			decoded = Base64.decode(base64Token);
		} catch (Exception e) {
			throw new Exception("Failed to decode basic authentication token");
		}
		String text = new String(decoded, StandardCharsets.UTF_8);
		return text;
	}
}
