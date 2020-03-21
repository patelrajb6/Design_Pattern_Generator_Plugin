package ConfigGenerator;

import DesignPatterns.Mediator;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class MediatorConfig {
    public static Config UserInput(String fname, String concMediator, String abUser,String MediatorObj,String ConcUser ) {
        String config;
        config = "DesignPattern {\n" +
                "    Name=Mediator\n ";            //getting the config and replacing it with the User Values
        config += String.format("AbstractMediator=%s\n", fname);
        config += String.format("ConcreteMediator=[%s]\n", concMediator);
        config += String.format("AbstractUser=%s\n", abUser);
        config += String.format("MediatorObject=%s\n", MediatorObj);
        config += String.format("ConcreteUser{%s}\n", ConcUser);
        config += "}";
        return ConfigFactory.parseString(config);
    }

}
