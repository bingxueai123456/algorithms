package com.encrypt;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Auther: eclair
 * @Date: 2020/10/27 22:18
 * @Description:
 */
public class SingatureDemo {
	public static void main(String[] args) throws Exception {
		String str = "你好";
		String algorithm = "RSA";
		String publicKeyStr = FileUtils.readFileToString(new File("a.pub"));
		String privateKeyStr = FileUtils.readFileToString(new File("a.pri"));
		KeyFactory instance = KeyFactory.getInstance(algorithm);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
		PrivateKey privateKey = instance.generatePrivate(pkcs8EncodedKeySpec);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
		PublicKey publicKey = instance.generatePublic(x509EncodedKeySpec);

		String sha256withrsa = getSignature(str, "sha256withrsa", privateKey);
		System.out.println(sha256withrsa);
		boolean sha256withrsa1 = verifySignature(str, "sha256withrsa", publicKey, sha256withrsa);
		System.out.println(sha256withrsa1);

	}

	private static boolean verifySignature(String str, String sha256withrsa, PublicKey publicKey, String sha256withrsa1)throws Exception {		Signature instance = Signature.getInstance(sha256withrsa);
		Signature signature = Signature.getInstance(sha256withrsa);
		signature.initVerify(publicKey);
		signature.update(str.getBytes());
		boolean verify = signature.verify(Base64.getDecoder().decode(sha256withrsa1));
		return verify;
	}

	private static String getSignature(String str, String sha256withrsa, PrivateKey key) throws Exception{
		Signature instance = Signature.getInstance(sha256withrsa);
		instance.initSign(key);
		instance.update(str.getBytes());
		byte[] sign = instance.sign();
		return Base64.getEncoder().encodeToString(sign);
	}
}
