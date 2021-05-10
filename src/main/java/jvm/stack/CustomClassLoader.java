package jvm.stack;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Auther: eclair
 * @Date: 2020/10/31 16:39
 * @Description:
 */
public class CustomClassLoader extends URLClassLoader {
	public CustomClassLoader(URL[] urls) {
		super(urls);
	}

	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(contextClassLoader);
	}
}
