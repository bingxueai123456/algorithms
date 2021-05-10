package com.reactor;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Auther: eclair
 * @Date: 2019/5/16 21:52
 * @Description:
 */
public class TestRsa {
	private static final int MAX_ENCRYPT_BLOCK = 117;
	private static final int MAX_DECRYPT_BLOCK = 128;

	public static KeyPair getKeyPair() throws Exception {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		return generator.generateKeyPair();
	}

	public static PrivateKey getPrivateKey(String privateKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
		return keyFactory.generatePrivate(keySpec);
	}

	public static PublicKey getPublicKey(String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
		return keyFactory.generatePublic(keySpec);
	}

	public static String encrypt(String data, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int inputLen = data.getBytes().length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offset = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offset > 0) {
			if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
			}
			out.write(cache, 0, cache.length);
			i++;
			offset = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return new String(Base64.encodeBase64String(encryptedData));
	}

	public static String decrypt(String data, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] dataBytes = Base64.decodeBase64(data);
		int inputLen = dataBytes.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offset = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offset > 0) {
			if (inputLen - offset > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
			}
			out.write(cache, 0, cache.length);
			i++;
			offset = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		// 解密后的内容
		return new String(decryptedData, "UTF-8");
	}

	public static String sign(String data, PrivateKey privateKey) throws Exception {
		byte[] keyBytes = privateKey.getEncoded();
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey key = keyFactory.generatePrivate(keySpec);
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(key);
		signature.update(data.getBytes());
		return new String(Base64.encodeBase64(signature.sign()));
	}

	public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
		byte[] keyBytes = publicKey.getEncoded();
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey key = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(key);
		signature.update(srcData.getBytes());
		return signature.verify(Base64.decodeBase64(sign.getBytes()));
	}

	public static void main(String[] args) {
		try {
			// 生成密钥对
			KeyPair keyPair = getKeyPair();
//			String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
			String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJuCzB1HWHfFHM8OgA4VNv2ChkQw/thb2jAfDmsmTBJB6JZngMpfUocbF0VMNLEj1Y1yiQ/bb6oXROj/TOgCXaPVsone8Ek40FpD3tE2lykDsPI9FZV+U4t27agAGVbtDjQrBs+KH1VX5WKBU+17KLU0lepmUiy+pOqV2FwrVyvpAgMBAAECgYBOYNsNerMNEozVkyJZdrnTheC0J9maJJlQnaZ/vLqkjRF0FNClruuCatYMuD7qdPwiIXMRSfD60mWJ5XrWiLK2occv3m5F88UCkAlIQYMM+KyzME9gSpCt4sGvhdNxZ9Ddjj2Y26OiRWHTz2bIPgZksR8YmN74rwKevICZb/DWTQJBANbANOELbjD22f5OYQ2EaarkMIGm0JdPWgb/qyW6/9OjFQxRmhQ7qEM2kviRNbYaX1S9JyNSvp2Uy3xPXWhcrecCQQC5YaEEcxn+ZQD5B//qHX5M/6lpYjl5nQp7GeQWO2hpVGMW1xGw6Wt1e7ggZfcPZk+tjIhz5dGkoS+LgCkJCf2vAkAEHnGlM1vssn9h4kz4PmbscS4A/psXm90FL1gwBBMuEv2vW81RwActKtnMzLQ0BvN3I52hjs+7JAR3IOqQut5XAkB1Bi9aGrru/53ElwszJBklzEKKlwoHPl5uZWK5shjwXOgMdAnj7wX57PbH5PY4T3K+8Lb2yr4zudjErEu7PAZbAkEAze/qqeCSlwwuvAf86WmYcnZIdt1HUylnIctN7DeuTjhnlSJVlWq5tgfYw1WYFz5+peamfMMGHyervnJT67qLIQ==";
//			String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbgswdR1h3xRzPDoAOFTb9goZEMP7YW9owHw5rJkwSQeiWZ4DKX1KHGxdFTDSxI9WNcokP22+qF0To/0zoAl2j1bKJ3vBJONBaQ97RNpcpA7DyPRWVflOLdu2oABlW7Q40KwbPih9VV+VigVPteyi1NJXqZlIsvqTqldhcK1cr6QIDAQAB";
			System.out.println("私钥:" + privateKey);
			System.out.println("公钥:" + publicKey);
//			// RSA加密
			String data = "待加密的文字内容fsdfsfasdfsd9ofslnmfsdf0sl123jsf0lj3120jl1j30sjljsf0jrqwerwerafsdfqwerqwsfasfsfsdfwqerqwfsafasfasfwqrqrq";
			String encryptData = encrypt(data, getPublicKey(publicKey));
			System.out.println("加密后内容:" + encryptData);
//			// RSA解密
			String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
			System.out.println("解密后内容:" + decryptData);
//			// RSA签名
			String sign = sign(data, getPrivateKey(privateKey));
			// RSA验签
			boolean result = verify(data, getPublicKey(publicKey), sign);
			System.out.print("验签结果:" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("加解密异常");
		}
	}
}

