package ConfigGenerator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TemplateConfig {
    public static Config UserInput(String AbstractClass, String TemplateMethod, String AbstractMethods, String ConcreteClasses) {
        String config;
        config = "DesignPattern {\n" +
                "    Name=Template\n ";            //getting the config and replacing it with the User Values
        config += String.format("AbstractClass=%s\n", AbstractClass);
        config += String.format("TemplateMethod=%s\n", TemplateMethod);
        config += String.format("AbstractMethods=[%s]\n", AbstractMethods);
        config += String.format("ConcreteClasses=[%s]\n", ConcreteClasses);
        config += "}";
        return ConfigFactory.parseString(config);
    }

}
