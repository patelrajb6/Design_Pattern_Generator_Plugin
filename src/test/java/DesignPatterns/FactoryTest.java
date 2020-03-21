package DesignPatterns;

import ConfigGenerator.BuilderConfig;
import ConfigGenerator.FactoryConfig;
import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryTest {
    private Factory test1,test2;
    @Before
    public void init(){
        test1=new Factory(null);    //the default behaviour
        Config con = FactoryConfig.UserInput("Honda", "interface", "c1,c2");
        test2= new Factory(con);    //the user choice behaviour
    }

    @Test
    public void getFactoryName() {
        assertEquals("Factory1", test1.getFactoryName());
        assertEquals("Honda", test2.getFactoryName());
    }

    @Test
    public void getProductInterface() {
        assertEquals("Product", test1.getProductInterface());
        assertEquals("interface", test2.getProductInterface());
    }

    @Test
    public void getConcreteProducts() {
        String[] ans={"c1","c2"};
        assertEquals(ans, test2.getConcreteProducts().toArray());
    }
}