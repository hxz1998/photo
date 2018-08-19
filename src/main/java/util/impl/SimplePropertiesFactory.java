package util.impl;

import org.springframework.core.io.ClassPathResource;
import util.PropertiesFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SimplePropertiesFactory implements PropertiesFactory {

    private Properties properties;

    public SimplePropertiesFactory() {
        properties = new Properties();
        try {
            properties.load(new ClassPathResource("keys.properties").getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getPropertiesValue(String name) {
        return properties.getProperty(name);
    }
}
