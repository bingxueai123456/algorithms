package com.encrypt;

import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Auther: eclair
 * @Date: 2020/10/26 21:24
 * @Description:
 */
public class RsaDemo {
	public static void main(String[] args) throws Exception {
		String algorithm = "RSA";
		String str = "我是地球人";
//		generateKeytoFile("RSA", "a.pub", "a.pri");
		String publicKeyStr = FileUtils.readFileToString(new File("a.pub"));
		String privateKeyStr = FileUtils.readFileToString(new File("a.pri"));
		KeyFactory instance = KeyFactory.getInstance(algorithm);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
		PrivateKey privateKey = instance.generatePrivate(pkcs8EncodedKeySpec);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
		PublicKey publicKey = instance.generatePublic(x509EncodedKeySpec);
		String encrypt = encrypt(algorithm, privateKey, str);
		System.out.println(encrypt);
		String decrpty = decrpty(algorithm, publicKey, encrypt);
		System.out.println(decrpty);


	}

	public static void generateKeytoFile(String algorithm, String publicPath, String privatePath) throws Exception {
		KeyPairGenerator rsa = KeyPairGenerator.getInstance(algorithm);
		KeyPair keyPair = rsa.generateKeyPair();
		PrivateKey aPrivate = keyPair.getPrivate();
		PublicKey aPublic = keyPair.getPublic();
		byte[] privateStr = aPrivate.getEncoded();
		byte[] publicStr = aPublic.getEncoded();

		String privateEncodeString = Base64.getEncoder().encodeToString(privateStr);
		String publicEncodeString = Base64.getEncoder().encodeToString(publicStr);

		FileUtils.writeStringToFile(new File(publicPath), publicEncodeString);
		FileUtils.writeStringToFile(new File(privatePath), privateEncodeString);

	}

	public static String encrypt(String algorithm, Key key, String content) throws Exception {
		Cipher instance = Cipher.getInstance(algorithm);
		instance.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytes = instance.doFinal(content.getBytes());
		return Base64.getEncoder().encodeToString(bytes);
	}

	public static String decrpty(String algorithm, Key key, String content) throws Exception {
		Cipher instance = Cipher.getInstance(algorithm);
		instance.init(Cipher.DECRYPT_MODE, key);
		return new String(instance.doFinal(Base64.getDecoder().decode(content)));
	}
}
