package DesignPatterns;

import ConfigGenerator.AbstractConfig;
import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractFactoryTest {
    private AbstractFactory test1,test2;    //instances to check for default and user configs
    @Before
    public void init(){
        test1= new AbstractFactory(null);   //default behaviour
        Config con= AbstractConfig.UserInput("testInterface", "test", "test2");
        test2= new AbstractFactory(con);    //user behaviour

    }
    @Test
    public void Test1(){
        String factory=test1.getConfObject();
        assertEquals("class DesignPatterns.AbstractFactory", test1.getClass().toString());
        assertEquals("testing for correct Config","AbstractFactory",factory);
    }
    @Test
    public void Test2(){
        String factory=test2.getConfObject();
        assertEquals("class DesignPatterns.AbstractFactory", test2.getClass().toString());
        assertEquals("testing for correct Config","testInterface",factory);
    }


}