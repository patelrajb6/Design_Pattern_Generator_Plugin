package ConfigGenerator;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;

public class AbstractConfig{

    public static Config UserInput(String factoryInterface, String ConcreteFactories, String  ProductsInterface ) {
        String config;      //loading a temp String for Configuration
        config = "DesignPattern {\n" +
                "    Name=AbstractFactory\n ";            //getting the config and replacing it with the User Values
        config += String.format("FactoryInterface=%s\n", factoryInterface);
        config += String.format("ConcreteFactories=[%s]\n", ConcreteFactories);
        config += String.format("ProductsInterface=[%s]\n", ProductsInterface);
        config += "}";
        return ConfigFactory.parseString(config);
    }
}
