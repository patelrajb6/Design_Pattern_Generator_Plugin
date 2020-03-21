package ConfigGenerator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class FacadeConfig  {

    public static Config UserInput(String facadeClass, String ConcFacade, String otherClasses) {
        String config;
        config = "DesignPattern {\n" +
                "    Name=Facade\n ";            //getting the config and replacing it with the User Values
        config += String.format("FacadeClass=%s\n", facadeClass);
        config += String.format("ConcreteFacade=[%s]\n", ConcFacade);
        config += String.format("Classes{%s}\n", otherClasses);
        config += "}";
        return ConfigFactory.parseString(config);
    }

}
