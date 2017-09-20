package test;

import org.apache.log4j.Logger;

public class Test {			
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Test.class);
		System.out.println(getClassResource(org.slf4j.spi.LocationAwareLogger.class));
	}
	
	public static String getClassResource(Class<?> klass) {
		return klass.getClassLoader().getResource(klass.getName().replace('.', '/') + ".class").toString();
	}
}
