package DesignPatterns;

import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

public class DesignPattern {
    Logger logger = LoggerFactory.getLogger(DesignPattern.class);
    HashMap<String,Runnable>Designs;
    public DesignPattern(String path, Config con) throws IOException {
        Designs= new HashMap<>();   //a hashmap which takes  design pattern as key and runs the value as code
        Designs.put("AbstractFactory", ()-> new AbstractFactory(con).GenerateCode(path));
        Designs.put("Builder", ()->new Builder(con).GenerateCode(path));
        Designs.put("Chain", ()->new Chain(con).GenerateCode(path));
        Designs.put("Facade", ()->new Facade(con).GenerateCode(path));
        Designs.put("Factory",()->new Factory(con).GenerateCode(path));
        Designs.put("Mediator",()-> new Mediator(con).GenerateCode(path));
        Designs.put("Template",()->new Template(con).GenerateCode(path));
        Designs.put("Visitor", ()->new Visitor(con).GenerateCode(path));

    }
    public void getDesignPattern(String pattern) {
        try{
            Designs.get(pattern).run(); //runs the pattern asked for
        }catch (Exception e)
        {
            logger.error(getClass()+"::getDesignPattern() Failed",e);
        }
    }

}


