package reflectionWorks;

import java.awt.Graphics2D;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class AddNewClassWithPaintMethod {

	private URL[] classLoaderURLPath;
	private String reflectedPath;
	private URLClassLoader classLoader;
	private Class<?> userClass;
	private Method paintMethod;
	private Object reflectionObject;
	@SuppressWarnings("unused")
	private File file;

	public AddNewClassWithPaintMethod(URL[] classLoaderURLPath, String reflectedPath) {
		this.classLoaderURLPath = classLoaderURLPath;
		this.reflectedPath = reflectedPath;
	}

	public void generateClass() throws MalformedURLException, ClassNotFoundException {
		classLoader = new URLClassLoader(classLoaderURLPath);
		userClass = classLoader.loadClass(reflectedPath);
	}

	public void generatePaintMethodAndObject() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		paintMethod = userClass.getMethod("paint", Graphics2D.class);
		Constructor<?> cons[] = userClass.getConstructors();
		reflectionObject = cons[0].newInstance();
	}
	public void invokePaintMethod(Graphics2D g) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		paintMethod.invoke(reflectionObject, g);
	}

}
