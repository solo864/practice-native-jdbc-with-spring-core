package util;


import java.util.Properties;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PropertiesUtil {

    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    @SneakyThrows

        try (var stream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(stream);
        }
    }
}
