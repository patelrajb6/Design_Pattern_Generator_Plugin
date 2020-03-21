package ConfigGenerator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class FactoryConfig {
    public static Config UserInput(String fname, String productinfce, String concproducts) {
        String config;
        config = "DesignPattern {\n" +
                "    Name=Factory\n ";            //getting the config and replacing it with the User Values
        config += String.format("FactoryName=%s\n", fname);
        config += String.format("ProductInterface=%s\n", productinfce);
        config += String.format("ConcreteProducts=[%s]\n", concproducts);
        config += "}";
        return ConfigFactory.parseString(config);
    }

}
