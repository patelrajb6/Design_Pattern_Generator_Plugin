package DesignPatterns;
import com.typesafe.config.Config;

import java.io.IOException;
import java.util.List;

public class Template extends DesignFactory {
    //attributes
    private Config conf;
    private String AbstractClass,TemplateMethod;
    private List<String>AbstractMethods,ConcreteClasses;
    Template(Config con ) {    //constructor
        super();
        try {
            if(con!=null)
                this.conf=con;
            else
                this.conf= getConfig("TemplateConfig.conf");    //loads the config file
            AbstractClass=conf.getString("DesignPattern.AbstractClass");    //gets the name of the abstract class
            TemplateMethod=conf.getString("DesignPattern.TemplateMethod");  //gets the final template method
            AbstractMethods=conf.getStringList("DesignPattern.AbstractMethods");    //gets a list of abstract methods to be implemented
            ConcreteClasses= conf.getStringList("DesignPattern.ConcreteClasses");   //gets the list of the concrete class implementing the abstract class
            logger.info(getClass().toString()+"::Template success");
        }catch (Exception e){
            logger.error(getClass()+"::constructor failed",e);
        }
    }
    void createAbstractClass(String path) {
        try{    //generates the abstract class with abstract methods
            String syntax=String.format("public abstract class %s {\n",AbstractClass);
            for(String method: AbstractMethods) //adds the abstract methods
            {
                syntax+=String.format("\tabstract void %s();\n", method);
            }
            syntax+=String.format("\tpublic final void %s(){\n", TemplateMethod);   //adds the template method
            for(String method: AbstractMethods) //calls the abstract methods in the template method
            {
                syntax+=String.format("\t\t%s();\n", method);
            }
            syntax+="\t}\n}";
            generateFile(syntax, AbstractClass,path);    //generates the java file
            logger.info(getClass().toString()+"::createAbstractClass success");
        }catch (Exception e)
        {
            logger.error(getClass()+"::createAbstractClass() failed",e);
        }
    }

    @Override
    void createClass(String name,String path) throws IOException {
        try{
            for(String concreteClass: ConcreteClasses)  //generating the ConcreteClasses
            {
                String syntax=String.format("public class %s extends %s {\n", concreteClass,name);
                for(String method: AbstractMethods) // implementing the abstract methods
                {
                    syntax+="\t@Override\n";
                    syntax+=String.format("\tvoid %s(){\n\n\t}\n", method);
                }
                syntax+="}";
                generateFile(syntax, concreteClass,path);    //generates the java file
                logger.info(getClass().toString()+"::createClass success");
            }
        }catch(Exception e){
            logger.error(getClass()+"::createClass() failed",e);
        }
    }

    @Override
    public void GenerateCode(String path) {
        try{
            createAbstractClass(path);  //creates the abstract class
            createClass(AbstractClass,path); //creates the concrete class
            CheckRepeatedFiles();
            logger.info(getClass().toString()+"::GenerateCode success");
        }catch (Exception e) {
            logger.error(getClass()+"::GenerateCode() failed",e);
        }
    }

    public String getAbstractClass() {
        return AbstractClass;
    }

    public List<String> getAbstractMethods() {
        return AbstractMethods;
    }
}
