package DesignPatterns;

import ConfigGenerator.MediatorConfig;
import ConfigGenerator.TemplateConfig;
import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemplateTest {
    private Template test1,test2;
    @Before
    public void init(){
        test1=new Template(null);    //the default behaviour
        Config con = TemplateConfig.UserInput("aClass", "method", "a1,a2", "c1,c2");
        test2= new Template(con);    //the user choice behaviour
    }
    @Test
    public void getAbstractClass() {
        assertEquals("TemplateClass", test1.getAbstractClass());
        assertEquals("aClass", test2.getAbstractClass());
    }

    @Test
    public void getAbstractMethods() {
        String[] ans={"a1","a2"};
        assertEquals(ans,test2.getAbstractMethods().toArray());
    }
}