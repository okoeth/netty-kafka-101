package com.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	private static volatile Properties instance = null;

	private PropertyReader() {

	}

	public static Properties propertyReader() {
		try {
			InputStream io = PropertyReader.class.getResourceAsStream("/config.properties");
			if (instance == null) {
				instance = new Properties();
				instance.load(io);
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return instance;
	}
}
