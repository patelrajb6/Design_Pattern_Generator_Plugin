package PlugInViews;

import ConfigGenerator.AbstractConfig;
import DesignPatterns.DesignPattern;
import com.typesafe.config.Config;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class AbstractView extends JFrame {
    private JTextField AbstractInterface;
    private JTextField ConcreteFact;
    private JTextField productinterfaces;
    private JButton okButton;
    private JButton defaultsButton;
    private JLabel ProductInterfaces;
    private JPanel rootPanel;
    private JLabel ConcreteFactories;
    private AbstractConfig UserGenerated;
    public AbstractView(String path){
        setTitle("Abstract Factory");
        setLocationRelativeTo(null);
        //UserGenerated=new AbstractConfig();
        okButton.addActionListener(e->{
            String products=productinterfaces.getText();
            String ConCreteFactories=ConcreteFact.getText();
            String AbstractFactoryInterface= AbstractInterface.getText();
            Config config=AbstractConfig.UserInput(AbstractFactoryInterface, ConCreteFactories, products);

            try {
                new DesignPattern(path,config).getDesignPattern("AbstractFactory");
                setVisible(false);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        defaultsButton.addActionListener(e-> {
            try {
                new DesignPattern(path,null).getDesignPattern("AbstractFactory");
                setVisible(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        setContentPane(rootPanel);
        setVisible(true);
        pack();
    }
}
