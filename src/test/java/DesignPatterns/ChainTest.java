package DesignPatterns;

import ConfigGenerator.ChainConfig;
import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class ChainTest {
    private Chain test1,test2;
    @Before
    public void init()
    {
        Config con= ChainConfig.UserInput("handle", "recvr1,recvr2");
        test1= new Chain(null); //testing the default behaviour
        test2= new Chain(con);  //testing the user generated behaviour
    }
    @Test
    public void getHandler() {  //checking the handler
        assertEquals("Handler", test1.getHandler());
        assertEquals("handle", test2.getHandler());
    }

    @Test
    public void getReceivers() {    //checking the recievers
        String[] ans={"recvr1","recvr2"};
        assertEquals(ans,test2.getReceivers().toArray());
    }
}