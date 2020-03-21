package DesignPatterns;

import ConfigGenerator.FactoryConfig;
import ConfigGenerator.MediatorConfig;
import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MediatorTest {
    private Mediator test1,test2;
    @Before
    public void init(){
        test1=new Mediator(null);    //the default behaviour
        Config con = MediatorConfig.UserInput("mediator", "m1", "aUser", "obj", "cU1=obj1");
        test2= new Mediator(con);    //the user choice behaviour
    }

    @Test
    public void getAbstractMediator() {
        assertEquals("Mediator", test1.getAbstractMediator());
        assertEquals("mediator", test2.getAbstractMediator());
    }

    @Test
    public void getAbstractUser() {
        assertEquals("User", test1.getAbstractUser());
        assertEquals("aUser", test2.getAbstractUser());
    }
}