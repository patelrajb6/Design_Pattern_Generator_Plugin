package DesignPatterns;

import com.typesafe.config.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Chain extends DesignFactory {
    private Config conf;
    private String Handler;
    private List<String>Receivers;
    private ArrayList<String> Methods;
    private String instance;
    Chain(Config con)
    {
        try{
            if(con!=null)
                this.conf=con;
            else
                this.conf= getConfig("ChainConfig.conf");      //getting the config file
            this.Handler= this.conf.getString("DesignPattern.Handler");        //getting the handler abstract class
            this.Receivers=this.conf.getStringList("DesignPattern.Receiver");  // getting the concrete handlers
            this.Methods= new ArrayList<String>();          //storing the mandatory methods for the abstract class
            InheritedMethods();                             //adds the methods
            logger.info(getClass().toString()+"::Chain success");
        }catch(Exception e)
        {
            logger.error(getClass()+"Constructor failed",e);
        }


    }
    void InheritedMethods(){
        this.Methods.add("handleRequest()");
        this.Methods.add("canHandleRequest()");
        logger.info(getClass().toString()+"::InheritedMethods success");
    }

    public void GenerateCode(String path) {
        try{
            createAbstractClass(this.Handler,path);      //generating the abstract class needed for handling the request
            createClass(this.Handler,path);              //generates the classes which will handle the request
            CheckRepeatedFiles();
            logger.info(getClass().toString()+"::GenerateCode success");
        }catch(Exception e)
        {
            logger.error(getClass()+"::GenerateCode() failed",e);
        }

    }

    void createAbstractClass(String name,String path) throws IOException {
        instance="instance";     //instance of the class Handler
        String class_syntax=String.format("public abstract class %s {\n\tprivate %s %s;\n\t",name,name,instance);
        class_syntax+=String.format("public %s (%s %s){ ", name,name,instance); //constructor of the class
        class_syntax+=String.format("\n\t this.%s = %s;\n\t}\n", instance,instance); //assigning instance
        class_syntax+=String.format("\tabstract public Boolean canHandleRequest(); \n");    //method to check if the handler class can handle the request
        class_syntax+=String.format("\tpublic void handleRequest() {\n\t\tif (%s!= null) { \n\t",instance); //checks if the handler has instance or not
        class_syntax+=String.format("\t\t%s.handleRequest();\n\t\t}\n\t}\n", instance);     //if yes then handle the request
        class_syntax+="}";
        generateFile(class_syntax, name,path);       //generating the .java file
        logger.info(getClass().toString()+"::createAbstractClass success");

    }

    void createClass(String AbstractClass,String path) throws IOException {
        for(String handler: Receivers)
        {
            String class_syntax=String.format("public class %s extends %s {\n\t", handler,AbstractClass);
            class_syntax+=String.format("public %s(%s %s) {\n\t",handler,AbstractClass,instance);
            class_syntax+=String.format("\tsuper(%s);\n\t}\n", instance);
            class_syntax+=String.format("\t@Override\n\t");
            class_syntax+=String.format("\tpublic void handleRequest() {\n\t\tif (canHandleRequest()) {\n\t} else { \n\t",instance);
            class_syntax+=String.format("\t\tsuper.handleRequest();\n\t\t}\n\t}\n}", instance);
            generateFile(class_syntax, handler,path);

        }
        logger.info(getClass().toString()+"::createClass success");
    }

    public String getHandler() {
        return Handler;
    }

    public List<String> getReceivers() {
        return Receivers;
    }
}
