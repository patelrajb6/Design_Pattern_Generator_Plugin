package ConfigGenerator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class BuilderConfig  {

    public static Config UserInput(String product, String Interface, String  parts,String builderClass) {
        String config;      //loading a temp String for Configuration
        config = "DesignPattern {\n" +
                "    Name=Builder\n ";            //getting the config and replacing it with the User Values
        config += String.format("Product=%s\n", product);
        config += String.format("Interface=%s\n", Interface);
        config += String.format("Parts=[%s]\n", parts);
        config += String.format("BuilderClasses=[%s]\n", builderClass);
        config += "}";
        return ConfigFactory.parseString(config);
    }


}
