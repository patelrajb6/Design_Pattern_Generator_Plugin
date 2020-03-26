package DesignPatterns;

import NameClashDetection.NameClassErrorDialog;
import PlugInViews.ConfirmationDialog;
import com.typesafe.config.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AbstractFactory extends DesignFactory {
    private String factoryName;     //stores the factoryName
    private ArrayList<String> factoryMethods;    //stores the list of methods of the interface
    private ArrayList<String> productsList; //stores the methods of the product interface
    private Config conf;    // config
    private List<String> ProductsInterface;  //Stores the product Interface
    private List<String> ConcreteFactories; //Concrete factories

    AbstractFactory(Config con){
        if(con!=null)
            this.conf=con;
        else
            this.conf = getConfig("AbstractFactoryConfig.conf");   //loading the config file

        this.factoryName= conf.getString("DesignPattern.FactoryInterface"); //gets the factory name
        this.ProductsInterface=conf.getStringList("DesignPattern.ProductsInterface");   //gets the list of Product Interfaces
        this.ConcreteFactories= conf.getStringList("DesignPattern.ConcreteFactories");  //gets the concrete implementation of the factory interface
        this.factoryMethods= new ArrayList<String>();   //stores the methods of the factory
        this.productsList= new ArrayList<String>(); //stores the methods of products

    }
    public String getConfObject(){                              //testing purposes
        return this.factoryName;
    }
    public void GenerateCode(String path) {
        try{
            createInterface(factoryName,path);   //generates the Interface of the DesignPatterns.Factory
            createClass(factoryName,path);   //generates the Concrete Implementation of the DesignPatterns.Factory
            createProductInterface(path);   //generates the product Interface
            createProductClass(path);       //generates the Concrete Implementation of Product
            CheckRepeatedFiles();
        } catch (Exception e){
            logger.error(getClass()+"::GenerateCode() failed",e);
        }

    }

    public void createInterface(String name,String path) throws IOException {  //generating the interface for the abstract factory with choice
        String methods;
        String Interface_Syntax= String.format("public interface %s {\n ",name);    //generates the syntax
        for(String in: ProductsInterface)
        {
            methods=addMethods(in); //gets the syntax of the methods
            factoryMethods.add(methods);    //storing the methods for implementation in concrete implementation
            methods+=";";
            Interface_Syntax+="\t"+methods+"\n";
        }
        Interface_Syntax+="}";
        try{
            generateFile(Interface_Syntax, name,path);       //generates the java file
            logger.info("Interface created: "+name);
        } catch (Exception e)
        {
            logger.error("failed to generate interface: "+name);
        }

    }

    public void createClass(String name,String path)  {
        String class_syntax;
        for(String in: ConcreteFactories)
        {
            class_syntax=String.format("public class %s implements %s {\n", in,factoryName );
            for(String s: factoryMethods)
            {
                String[] returnVal=s.split(" ");
                class_syntax+="\t"+s+"{\n\t return new "+returnVal[0] +"();\n}\n";
            }
            class_syntax+="}";
            try
            {
                generateFile(class_syntax, in,path);
                logger.info("class created: "+name);
            } catch (Exception e)
            {
                logger.error("failed to generate class: "+name);
            }

        }

    }

    private void createProductInterface (String path) {
        String product_syntax;
        for(String product: ProductsInterface)
        {
            product_syntax= String.format("public interface %s {\n}", product);
            try{
                generateFile(product_syntax, product,path);
                logger.info("Product interface created: "+product);
            } catch (Exception e)
            {
                logger.error("failed to generate Interface: "+product);
            }

        }


    }

    private void createProductClass(String path) {
        String product_syntax;
        String productName;
        for(String interfce: ProductsInterface )
        {
            for(String product: ConcreteFactories)
            {
                productName=product+interfce;
                product_syntax=String.format("public class %s implements %s {\n", productName,interfce );
                product_syntax+="}";
                try
                {
                    generateFile(product_syntax, productName,path);
                    logger.info("class created: "+productName);
                }
                catch (Exception e)
                {
                    logger.error("failed to generate Product class: "+productName);
                }

            }
        }

    }

    private String addMethods (String methods)  {
        productsList.add(methods);
        String method= String.format("%s create_%s()",methods,methods);
        return method;
    }


}
