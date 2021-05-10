package com.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Auther: eclair
 * @Date: 2020/10/15 16:09
 * @Description:
 */
public class DesDemo {
	public static void main(String[] args) throws Exception {
		String input = "东阳";
		String key = "12345678";
		//算法
		String transformtion = "DES";
		Cipher des = Cipher.getInstance(transformtion);
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), transformtion);
		des.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] bytes = des.doFinal(input.getBytes());
		System.out.println(new String(Base64.getEncoder().encode(bytes)));


		des.init(Cipher.DECRYPT_MODE,secretKeySpec);
		byte[] bytes1 = des.doFinal(bytes);
		System.out.println(new String(bytes1));

	}
}
