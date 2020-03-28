package DesignPatterns;

import com.typesafe.config.Config;

import java.io.IOException;
import java.util.List;

public class Factory extends DesignFactory {
    private Config conf;    //config object reference
    private String FactoryName; //storing the DesignPatterns.Factory name
    private String ProductInterface;      //storing the Product Interface
    private List<String> ConcreteProducts;      //Storing the list of concrete implementation

    public Factory(Config con)
    {
        super();
        if(con!=null)
            this.conf=con;
        else
            this.conf= getConfig("FactoryConfig.conf");     //loading the config
        this.FactoryName=conf.getString("DesignPattern.FactoryName");      // getting the name of the factory
        this.ProductInterface= conf.getString("DesignPattern.ProductInterface"); //getting the product interface
        this.ConcreteProducts=conf.getStringList("DesignPattern.ConcreteProducts"); //getting the list of concrete classes
        logger.info(getClass().toString()+"::Factory success");
    }
    private void createProducts(String path) throws IOException {
        for(String classes: ConcreteProducts)
        {       //generating the syntax
            String product_syntax=String.format("public class %s implements %s {\n",classes,ProductInterface );
            product_syntax+="\tpublic void doSomething(){\n\t\t //do something\n\t}\n}";
            generateFile(product_syntax, classes,path);//generating the file
            logger.info(getClass().toString()+"::createProducts success");
        }

    }
    private  void createInterface(String path) throws IOException {
        String interface_syntax=String.format("public interface %s {\n", ProductInterface); //generating interface for products
        interface_syntax+="\tvoid doSomething();\n}";
        generateFile(interface_syntax, ProductInterface,path); //generating the java file
        logger.info(getClass().toString()+"::createInterface success");
    }
    @Override
    void createClass(String name,String path) throws IOException {
        String class_syntax=String.format("public class %s {\n", name); //building the syntax
        for(String product:ConcreteProducts)    //creating methods for getting the concrete products
        {
            class_syntax+=String.format("\t public %s get%s(){\n", ProductInterface,product);
            class_syntax+=String.format("\t\t return new %s();\n",product);
            class_syntax+="\t}\n";
        }
        class_syntax+="}";
        generateFile(class_syntax, name,path);   //generating the java file
        logger.info(getClass().toString()+"::createClass success");
    }
    @Override
    public void GenerateCode(String path){
        try{
            createClass(FactoryName,path);   //creates the factory class
            createInterface(path);  //creates the Product Interface
            createProducts(path);   //creates the concrete product
            CheckRepeatedFiles();
            logger.info(getClass().toString()+"::GenerateCode success");
        }catch(Exception e)
        {
            logger.error(getClass()+"::GenerateCode() failed",e);
        }

    }

    public String getFactoryName() {
        return FactoryName;
    }

    public String getProductInterface() {
        return ProductInterface;
    }

    public List<String> getConcreteProducts() {
        return ConcreteProducts;
    }
}
