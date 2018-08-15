package util;

import java.util.Properties;

public interface PropertiesFactory {
    Properties getProperties();
    String getPropertiesValue(String name);
}
