package ConfigGenerator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ChainConfig {

    public static Config UserInput(String handler, String receivers ) {
        String config;
        config = "DesignPattern {\n" +
                "    Name=Chain \n";            //getting the config and replacing it with the User Values
        config += String.format("Handler=%s\n", handler);
        config += String.format("Receiver=[%s]\n", receivers);
        config += "}";
        return ConfigFactory.parseString(config);
    }

}
