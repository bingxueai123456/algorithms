package com.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Auther: eclair
 * @Date: 2020/10/15 16:09
 * @Description:
 */
public class AesDemo {
	public static void main(String[] args) throws Exception {
		String input = "东阳dongyanggg";
		//16个字节
		String key = "1234567812345678";
		//算法
		String transformtion = "AES/CBC/NoPadding";
		Cipher des = Cipher.getInstance(transformtion);
		IvParameterSpec ivParameterSpec = new IvParameterSpec("8765432187654321".getBytes());
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
		des.init(Cipher.ENCRYPT_MODE, secretKeySpec,ivParameterSpec);
		byte[] bytes = des.doFinal(input.getBytes());
		System.out.println(new String(Base64.getEncoder().encode(bytes)));

		IvParameterSpec ivParameterSpec2 = new IvParameterSpec("8765432187654321".getBytes());
		des.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec2);
		byte[] bytes1 = des.doFinal(bytes);
		System.out.println(new String(bytes1));

	}
}
