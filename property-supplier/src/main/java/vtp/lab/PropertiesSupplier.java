package main.java.vtp.lab;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;


public class PropertiesSupplier {
    private static Properties globalProperties;
    private static ThreadLocal<Properties> threadProperties = new ThreadLocal<>();

    private static final String PROPERTY_NOT_FOUND_ERR_MSG = "Property %s was not found in system properties";

    public static void load(InputStream stream) {
        try {
            getThreadProperties().load(stream);
        } catch (IOException e) {
            throw new ResourseReadFailedException("Problem occurred during reading properties file", e);
        }
    }

    public static synchronized void loadGlobalProperties(InputStream stream) {
        try {
            getGlobalProperties().load(stream);
        } catch (IOException e) {
            throw new ResourseReadFailedException("Problem occurred during reading properties file", e);
        }
    }


    public static Integer getPropertyAsInteger(final String propertyName, final Integer defaultValue) {
        String value = get(propertyName);
        if (value != null) {
            return Integer.valueOf(value);
        } else {
            return defaultValue;
        }
    }

    public static Integer getPropertyAsInteger(final String propertyName) {
        String value = get(propertyName);
        if (value != null) {
            return Integer.valueOf(value);
        } else {
            throw new ResourseReadFailedException(PROPERTY_NOT_FOUND_ERR_MSG, propertyName);
        }
    }

    public static Long getPropertyAsLong(final String propertyName, final Long defaultValue) {
        String value = get(propertyName);
        if (value != null && !value.isEmpty()) {
            return Long.valueOf(value);
        } else {
            return defaultValue;
        }
    }

    public static Long getPropertyAsLong(final String propertyName) {
        String value = get(propertyName);
        if (value != null && !value.isEmpty()) {
            return Long.valueOf(value);
        } else {
            throw new ResourseReadFailedException(PROPERTY_NOT_FOUND_ERR_MSG, propertyName);
        }
    }

    public static Boolean getPropertyAsBoolean(final String propertyName, final Boolean defaultValue) {
        String value = get(propertyName);
        if (value != null) {
            return Boolean.valueOf(value);
        } else {
            return defaultValue;
        }
    }

    public static Boolean getPropertyAsBoolean(final String propertyName) {
        String value = get(propertyName);
        if (value != null) {
            return Boolean.valueOf(value);
        } else {
            throw new ResourseReadFailedException(PROPERTY_NOT_FOUND_ERR_MSG, propertyName);
        }
    }

    public static String getProperty(final String propertyName, final String defaultValue) {
        String value = get(propertyName);
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public static String getProperty(final String propertyName) {
        String value = get(propertyName);
        if (value != null) {
            return value;
        } else {
            throw new ResourseReadFailedException(PROPERTY_NOT_FOUND_ERR_MSG, propertyName);
        }
    }

    public synchronized static void setThreadProperty(String property, String value) {
        getThreadProperties().setProperty(property, value);
    }

    public synchronized static void setGlobalProperty(String property, String value) {
        getGlobalProperties().setProperty(property, value);
    }

    public static Properties getThreadProperties() {
        if (Objects.isNull(threadProperties.get())) {
            threadProperties.set(new Properties());
        }
        return threadProperties.get();
    }

    private synchronized static Properties getGlobalProperties() {
        if (Objects.isNull(globalProperties)) {
            globalProperties = new Properties(System.getProperties());
        }
        return globalProperties;
    }

    private static String get(String key) {
        //Firstly look to the global properties shared between all threads
        String globalProperty = globalProperties.getProperty(key, null);
        if (Objects.nonNull(globalProperty)) {
            return globalProperty;
        }

        //Then look to the system properties (for current Thread)
        String systemProperty = System.getProperties().getProperty(key, null);
        if (Objects.nonNull(systemProperty)) {
            return systemProperty;
        }

        //Otherwise look to the Thread properties
        return getThreadProperties().getProperty(key);
    }

    public static void loadSystemProperties() {
        getGlobalProperties().putAll(System.getProperties());
    }

    public static void loadEnvProperties() {
        getGlobalProperties().putAll(System.getenv());
    }
}
