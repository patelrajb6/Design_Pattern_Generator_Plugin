package DesignPatterns;
import com.typesafe.config.Config;

import java.util.List;

public class Visitor extends DesignFactory{
    //attributes
    private Config conf;
    private String AbstractElement,AbstractVisitor;
    private List<String>ConcreteElements,ConcreteVisitors;
    private String parameter;
    Visitor(Config con)
    {
        try{
            if(con!=null)
                this.conf=con;
            else
                conf= getConfig("VisitorConfig.conf");      //loading the config
            //getting the values from the config file
            AbstractElement=conf.getString("DesignPattern.AbstractElement");
            AbstractVisitor=conf.getString("DesignPattern.AbstractVisitor");
            ConcreteElements=conf.getStringList("DesignPattern.ConcreteElements");
            ConcreteVisitors=conf.getStringList("DesignPattern.ConcreteVisitors");
            parameter="visitor";
            logger.info(getClass().toString()+"::Visitor success");
        }catch (Exception e){
            logger.error(getClass()+"::constructor failed",e);
        }

    }
    private void createAbstractElementClass(String path) {
        try{    //generates the syntax for the abstract element
            String syntax=String.format("public abstract class %s {\n", AbstractElement);
            syntax+=String.format("\tpublic abstract void accept(%s %s);\n}", AbstractVisitor,parameter);
            generateFile(syntax, AbstractElement,path);  //generates the java file
            logger.info(getClass().toString()+"::createAbstractElementClass success");
        }catch (Exception e){
            logger.error(getClass()+"::createAbstractElementClass() failed",e);
        }
    }
    private void createElementClass(String path){
        try{
            for(String ConcreteClass: ConcreteElements)     //gets all the concrete implementations
            {   //building the string for the concreteElement class
                String syntax=String.format("public class %s extends %s {\n", ConcreteClass,AbstractElement);
                syntax+=String.format("\tpublic void accept(%s %s) {\n", AbstractVisitor,parameter);
                syntax+=String.format("\t\t%s.visit%s(this);\n\t}\n}", parameter,ConcreteClass);
                generateFile(syntax, ConcreteClass,path); //generates the java files
                logger.info(getClass().toString()+"::createElementClass success");
            }
        }catch (Exception e){
            logger.error(getClass()+"::createElementClass() failed",e);
        }
    }
    void createAbstractVisitorClass(String path) {
        try{    //creates a simple class
            String syntax=String.format("public abstract class %s {\n", AbstractVisitor);
            for(String element:ConcreteElements)    //adds the necessary methods
            {
                syntax+=String.format("\tpublic abstract void visit%s(%s e);\n", element,element);
            }
            syntax+="}";
            generateFile(syntax, AbstractVisitor,path);  //generates the java file
            logger.info(getClass().toString()+"::createAbstractVisitorClass success");
        }catch (Exception e){
            logger.error(getClass()+"::createAbstractVisitorClass() failed",e);
        }
    }
    @Override
    void createClass(String name,String path){
        try{    //creates the concrete classes which implements the abstract visitor
            for(String visitor:ConcreteVisitors)
            {
                String syntax=String.format("public class %s extends %s {\n", visitor,AbstractVisitor);
                for(String element:ConcreteElements)    //adds the necessary methods
                {
                    syntax+=String.format("\tpublic void visit%s(%s element){\n\t\t//do something\n\t}\n", element,element);
                }
                syntax+="}";
                generateFile(syntax, visitor,path);  //generates the java file
                logger.info(getClass().toString()+"::createClass success");
            }
        }catch (Exception e){
            logger.error(getClass()+"::createClass() failed",e);
        }
    }

    @Override
    public void GenerateCode(String path) {
        try{
            createAbstractElementClass(path);
            createAbstractVisitorClass(path);
            createElementClass(path);
            createClass(AbstractVisitor,path);
            logger.info(getClass().toString()+"::GenerateCode success");
        }catch (Exception e){
            logger.error(getClass()+"::GenerateCode() failed",e);
        }
    }

    public String getAbstractElement() {
        return AbstractElement;
    }

    public String getAbstractVisitor() {
        return AbstractVisitor;
    }
}
