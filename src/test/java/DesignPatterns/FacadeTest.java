package DesignPatterns;

import ConfigGenerator.FacadeConfig;
import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FacadeTest {
    private Facade test1,test2; //testing two instances one of user and one default
    @Before
    public void init(){
        test1= new Facade(null);    //default behaviours
        Config con= FacadeConfig.UserInput("facade", "f1,f2", "c1=obj2");
        test2= new Facade(con); //user behaviour
    }

    @Test
    public void getAbstractFacade() {
        assertEquals("Facade", test1.getAbstractFacade());
        assertEquals("facade", test2.getAbstractFacade());
    }

    @Test
    public void getConcreteFacade() {
        String[] ans={"f1","f2"};
        assertEquals(ans,test2.getConcreteFacade().toArray());
    }
}