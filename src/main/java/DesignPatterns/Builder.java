package DesignPatterns;

import com.typesafe.config.Config;

import java.util.List;

public class Builder extends DesignFactory {
    private Config conf;                        //using typesafe framework to get input from Config File
    private List<String>BuilderClasses,Methods; //extracting the list for BuilderClass and for the methods in interface from the ConFig File
    private String InterfaceName;               //extracting the InterfaceName from the Config file
    private String Object;                      //DesignPatterns.Builder Pattern needs Object to be structurally modified so it is that object

    public Builder(Config con) {           //Constructor
        try{
            if(con!=null)
                this.conf=con;
            else
                this.conf= getConfig("BuilderConfig.conf");            //loading the Config
            this.BuilderClasses=conf.getStringList("DesignPattern.BuilderClasses");    //Getting the classes which are builders of implementing the Interface
            this.Methods= conf.getStringList("DesignPattern.Methods");                 //Getting the Methods which are to be there in the Interface
            this.InterfaceName=conf.getString("DesignPattern.Name");                   //Getting the Interface name
            this.Object=conf.getString("DesignPattern.Product");                       //Getting the Initial Product
            logger.info(getClass().toString()+"::Builder success");

        }catch (Exception e){
            logger.error("Constructor failed",e);
        }

    }
    public String getConfObject(){                              //testing purposes
        return this.InterfaceName;
    }

    public void GenerateCode(String path) {
        try{
            createInterface(InterfaceName,path);                     //generates the Interface of the DesignPatterns.Builder
            createClass(InterfaceName,path);                         //generates the Concrete Classes implementing the DesignPatterns.Builder Interface
            createProduct(Object,path);                              //generates the class which makes the basic object
            CheckRepeatedFiles();
        logger.info(getClass().toString()+"::GenerateCode success");
        }catch (Exception e){
            logger.error(getClass()+"::GenerateCode() failed",e);
        }

    }

    public void createProduct(String name,String path) {
        try{
            String product_syntax;                          //String which would help in generating the syntax
            product_syntax=String.format("public class %s { \n\n}",name);       //generating the Syntax for the basic product
            generateFile(product_syntax, name,path);             //generates the file with the syntax
            logger.info(getClass().toString()+"::createProduct success");

        }catch (Exception e){
            logger.error("createProduct() failed",e);
        }

    }

    public void createInterface(String name,String path)  {
        try{
            String interface_syntax=String.format("public interface %s {\n\t", name);       //String of syntax
            for(String methds: Methods)                                                     //getting the  methods specified in the Config and adding them to syntax
            {
                interface_syntax+=methds+";"+"\n\t";
                interface_syntax+="";
            }
            interface_syntax+="}";
            generateFile(interface_syntax, name,path);               //Generate the java file
            logger.info(getClass().toString()+"::createInterface success");

        }catch (Exception e){
            logger.error("createInterface() failed",e);
        }

    }

    public void createClass(String name,String path)  {
        try{
            String class_syntax;
            String Instance =this.Object.substring(0, 1).toLowerCase() + this.Object.substring(1);
            for(String classes: BuilderClasses)                             //getting all the Concrete Classes and adding all the
            {
                class_syntax= String.format("public class %s implements %s {\n",classes,name);
                class_syntax+="\t"+this.Object+" "+Instance+";\n";
                for(String methds: Methods)
                {
                    class_syntax+="\t"+methds+" {\n\t}\n";
                }
                class_syntax+="\t"+this.Object+" build(){\n\t return this."+Instance+";\n\t}\n";                  //every concrete builder class needs a build() which builds the product
                class_syntax+="}";
                generateFile(class_syntax, classes,path);    //generating the java file
                logger.info(getClass().toString()+"::createClass success");
            }
        }catch (Exception e){
            logger.error(" createClass() failed following is stacktrace", e);
        }



    }

    public String getObject() {
        return Object;
    }

}
