package DesignPatterns;

import ConfigGenerator.BuilderConfig;
import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class BuilderTest {
    private Builder test1,test2;
    @Before
    public void init(){
        test1=new Builder(null);    //the default behaviour
        Config con = BuilderConfig.UserInput("product", "interface", "part1", "class1");
        test2= new Builder(con);    //the user choice behaviour
    }

    @Test
    public void getConfObject() {
            //checking for information about the product
        assertEquals("Builder", test1.getConfObject());
        assertEquals("Product", test1.getObject());
    }

}