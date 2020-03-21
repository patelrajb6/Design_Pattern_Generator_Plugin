package ConfigGenerator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class VisitorConfig {
    public static Config UserInput(String AbstractElement, String ConcreteElements, String AbstractVisitor, String ConcreteVisitors) {
        String config;
        config = "DesignPattern {\n" +
                "    Name=Template\n ";            //getting the config and replacing it with the User Values
        config += String.format("AbstractElement=%s\n", AbstractElement);
        config += String.format("ConcreteElements=[%s]\n", ConcreteElements);
        config += String.format("AbstractVisitor=%s\n", AbstractVisitor);
        config += String.format("ConcreteVisitors=[%s]\n", ConcreteVisitors);
        config += "}";
        return ConfigFactory.parseString(config);
    }

}
