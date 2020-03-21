package DesignPatterns;

import ConfigGenerator.TemplateConfig;
import ConfigGenerator.VisitorConfig;
import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VisitorTest {
    private Visitor test1,test2;
    @Before
    public void init(){
        test1=new Visitor(null);    //the default behaviour
        Config con = VisitorConfig.UserInput("E1", "e1,e2", "aVisitor", "v1,v2");
        test2= new Visitor(con);    //the user choice behaviour
    }
    @Test
    public void getAbstractElement() {
        assertEquals("Element",test1.getAbstractElement());
        assertEquals("E1", test2.getAbstractElement());
    }

    @Test
    public void getAbstractVisitor() {
        assertEquals("Visitor", test1.getAbstractVisitor());
        assertEquals("aVisitor",test2.getAbstractVisitor());
    }
}