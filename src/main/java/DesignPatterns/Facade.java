package DesignPatterns;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValue;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Facade extends DesignFactory {
    private Config conf;            //retrieving configurations
    private String AbstractFacade;  //storing the Name of the Abstract class
    private String Method;          //storing the only method of the abstract class that is needed
    private List<String> concreteFacade;    //list of all the concrete classes implementing the Abstract Class
    private Set<Map.Entry<String, ConfigValue>> Classes;        //storing the class and its object into set
    Facade(Config con) {
        super();
        if(con!=null)
            this.conf=con;
        else
            this.conf= getConfig("FacadeConfig.conf");     //loading the config
        this.AbstractFacade=conf.getString("DesignPattern.FacadeClass");        //retrieving the class
        this.concreteFacade=conf.getStringList("DesignPattern.ConcreteFacade"); //retrieving the list of Concrete class
        this.Classes=conf.getConfig("DesignPattern.Classes").entrySet();    //getting the Class and its object for flexibility of the user
        this.Method="void operation()";         //only mandatory method
        logger.info(getClass().toString()+"::Facade success");
    }
    @Override
    public void GenerateCode(String path){
        try{
            createAbstractClass(AbstractFacade,path);        //generates the abstract class
            createClass(AbstractFacade,path);        //generates the classes of the concrete implementation
            ProductClasses(path);       //generating decoupled products
            CheckRepeatedFiles();
            logger.info(getClass().toString()+"::GenerateCode success");
        }catch (Exception e)
        {
            logger.error(getClass()+"::GenerateCode() failed",e);
        }

    }
    @Override
    void createClass(String name,String path) throws IOException {
        for( String concreteClass: this.concreteFacade)     //getting the list of the  concrete classes
        {
            String class_syntax=String.format("public class %s extends %s {\n\t",concreteClass ,name);  //syntax building
            for(Map.Entry<String,ConfigValue> object: Classes)  //getting class and their instance  for declaration
            {
                class_syntax+=String.format("private %s %s;\n\t",object.getKey(),object.getValue().unwrapped());
            }
            class_syntax+=String.format("public %s() {\n\t", concreteClass); //constructor
            for(Map.Entry<String,ConfigValue> object: Classes)  //instantiating the classes
            {
                class_syntax+=String.format("\tthis.%s =new %s();\n\t",object.getValue().unwrapped(),object.getKey());
            }
            class_syntax+="}\n";
            class_syntax+="\tpublic "+this.Method+"{\n\t}\n}"; //implementing the only needed method
            generateFile(class_syntax, concreteClass,path);      //generating the file
            logger.info(getClass().toString()+"::createClass success");
        }
    }
    void createAbstractClass(String name,String path) throws IOException {
        String class_syntax=String.format("public abstract class %s {\n\t",name);         // generating syntax for abstract class
        class_syntax+= String.format("public abstract %s;\n}",this.Method );
        generateFile(class_syntax, name,path); //generating the file
        logger.info(getClass().toString()+"::createAbstractClass success");
    }
    void ProductClasses(String path) throws IOException {
        for(Map.Entry<String,ConfigValue> clas: Classes)    //gettting the class and its object but only using class to create the class
        {
           String product_syntax=String.format("public class %s {\n}", clas.getKey());  //generating the class which are decoupled
           generateFile(product_syntax, clas.getKey(),path); //generating the file
        }
        logger.info(getClass().toString()+"::ProductClasses success");
    }

    public String getAbstractFacade() {
        return AbstractFacade;
    }

    public List<String> getConcreteFacade() {
        return concreteFacade;
    }
}
