package DesignPatterns;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValue;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Mediator extends DesignFactory {
    private Config conf;    //variable to load the config file
    private String AbstractMediator;        //variable to store abstract class name for mediator
    private String AbstractUser;         //abstract class for the Users
    private List<String>ConcreteMediators;
    private Set<Map.Entry<String, ConfigValue>> ConcreteUsers;  //gets the subconfig
    private String AbstractMethod;
    private String MediatorObject;
    Mediator(Config con) {    //constructor
        super();
        try{
            if(con!=null)
                this.conf=con;
            else
                this.conf= getConfig("MediatorConfig.conf");   //loads the config file
            //loads the value of the config file into the following variables
            AbstractMediator=conf.getString("DesignPattern.AbstractMediator");
            AbstractMethod= "void mediate()";
            AbstractUser=conf.getString("DesignPattern.AbstractUser");
            ConcreteMediators=conf.getStringList("DesignPattern.ConcreteMediator");
            ConcreteUsers=conf.getConfig("DesignPattern.ConcreteUser").entrySet();
            MediatorObject=conf.getString("DesignPattern.MediatorObject");
            logger.info(getClass().toString()+"::Mediator success");
        }catch (Exception e) {
            logger.error("Mediator::Constructor failed",e);
        }
    }
    private void createAbstractMediator(String path) {
        try{
            String syntax=String.format("public abstract class  %s {\n", AbstractMediator);    //syntax for the abstract class
            syntax+=String.format("\tpublic abstract %s;\n", AbstractMethod);   //adding the abstract function
            syntax+="}";
            generateFile(syntax,AbstractMediator,path);  //generates the file
            logger.info(getClass().toString()+"::createAbstractMediator success");
        }catch (Exception e)
        {
            logger.error("Mediator::createAbstractMediator() failed",e);
        }
    }
    private void createMediatorClass(String path) {
        try{
            for(String className: ConcreteMediators)
            {
                //generates the syntax
                String syntax=String.format("public  class %s extends %s {\n",className,AbstractMediator);
                for(Map.Entry<String,ConfigValue> instances: ConcreteUsers) //generates the instances of the Users
                {
                    syntax+=String.format("\tprivate %s %s;\n", instances.getKey(),instances.getValue().unwrapped());
                }
                syntax+=String.format("\tpublic %s() {\n", className);
                for(Map.Entry<String,ConfigValue> instances: ConcreteUsers)
                {
                    syntax+=String.format("\t\tthis.%s= new %s();\n",instances.getValue().unwrapped() ,instances.getKey());
                }
                syntax+="\t}\n";
                syntax+=String.format("\tpublic %s {\n\t}\n}",AbstractMethod);
                generateFile(syntax, className,path);
                logger.info(getClass().toString()+"::createMediatorClass success");
            }
        }catch(Exception e)
        {
            logger.error(getClass()+"::createMediatorClass() failed", e);
        }

    }
    private void createAbstractUser(String path) { //creating the Abstract User syntax
        try{    //generates the needed syntax
            String syntax=String.format("public abstract class %s {\n", AbstractUser);
            syntax+=String.format("\t%s %s;\n",AbstractMediator,MediatorObject);
            syntax+=String.format("\tpublic %s (%s %s){\n",AbstractUser,AbstractMediator,MediatorObject);
            syntax+=String.format("\t\tthis.%s =%s;\n\t}\n}", MediatorObject,MediatorObject);
            generateFile(syntax, AbstractUser,path); //generates the java file
            logger.info(getClass().toString()+"::createAbstractUser success");
        }catch (Exception e)
        {
            logger.error(getClass()+"::createAbstractUser() failed", e);
        }
    }
    void createClass(String name,String path) throws IOException {
        try{
            for(Map.Entry<String,ConfigValue> className:ConcreteUsers)     //concrete class Implementation
            {   //generates the syntax
                String syntax=String.format("public class %s extends %s {\n",className.getKey(),name);
                syntax+=String.format("\tpublic %s(%s %s) {\n", className.getKey(),AbstractMediator,MediatorObject);
                syntax+=String.format("\t\tsuper(%s);\n\t}\n", MediatorObject);
                syntax+="\tvoid DoSomethingwithMediatorObject() {\n\t}\n}";
                generateFile(syntax, className.getKey(),path);    //generates the java file
                logger.info(getClass().toString()+"::createClass success");
            }

        }catch(Exception e){
            logger.error(getClass()+"::createClass() failed", e);
        }

    }
    public void GenerateCode(String path){
        try {
            createAbstractMediator(path); //generates the abstract mediator
            createMediatorClass(path);  //generates the concrete implementation of mediator
            createAbstractUser(path); //generates the abstract user class
            createClass(AbstractUser,path);  //generates concrete implementation of the User
            CheckRepeatedFiles();
            logger.info(getClass().toString()+"::GenerateCode success");
        }catch (Exception e){
            logger.error("Mediator::GenerateCode() failed",e);
        }

    }

    public String getAbstractMediator() {
        return AbstractMediator;
    }

    public String getAbstractUser() {
        return AbstractUser;
    }
}
