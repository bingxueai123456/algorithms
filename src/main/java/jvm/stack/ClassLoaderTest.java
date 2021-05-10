package jvm.stack;

/**
 * @Auther: eclair
 * @Date: 2020/10/31 16:09
 * @Description:
 */
public class ClassLoaderTest {
	public static void main(String[] args) {
//		URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
//		for (URL urL : urLs) {
//			System.out.println(urL.toExternalForm());
//		}
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(contextClassLoader);
	}

}
