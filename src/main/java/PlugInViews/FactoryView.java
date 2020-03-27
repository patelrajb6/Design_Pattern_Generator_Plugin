package PlugInViews;

import ConfigGenerator.FacadeConfig;
import ConfigGenerator.FactoryConfig;
import DesignPatterns.DesignPattern;
import com.typesafe.config.Config;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FactoryView extends JFrame {
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton defaultsButton;
    private JButton okButton;
    private JLabel title;
    private JLabel FactoryName;
    private JLabel ProductInterface;
    private JLabel ConcreteProducts;

    public FactoryView(String path) throws HeadlessException {
        setLocationRelativeTo(null);
        okButton.addActionListener(e->{
            try {
                Config con = FactoryConfig.UserInput(textField1.getText(), textField2.getText(),textField3.getText());
                new DesignPattern(path,con).getDesignPattern("Factory");
                setVisible(false);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        defaultsButton.addActionListener(e->{
            try {
                new DesignPattern(path,null).getDesignPattern("Factory");
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
