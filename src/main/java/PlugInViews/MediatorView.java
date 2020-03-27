package PlugInViews;

import ConfigGenerator.FactoryConfig;
import ConfigGenerator.MediatorConfig;
import DesignPatterns.DesignPattern;
import DesignPatterns.Mediator;
import com.typesafe.config.Config;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MediatorView extends JFrame {
    private JPanel rootPanel;
    private JTextField AbstractClassText;
    private JTextField ConcreteClassesText;
    private JTextField AbstractUserText;
    private JTextField MediatorObjectText;
    private JTextField ConcreteUserText;
    private JButton okButton;
    private JButton defaultsButton;
    private JLabel title;
    private JLabel AbstractMediator;
    private JLabel ConcreteMediator;
    private JLabel AbstractUser;

    public MediatorView(String path) throws HeadlessException {
        setLocationRelativeTo(null);
        okButton.addActionListener(e->{
            try {
                Config con = MediatorConfig.UserInput(AbstractClassText.getText(), ConcreteClassesText.getText(),AbstractUserText.getText(),MediatorObjectText.getText(),ConcreteUserText.getText());
                new DesignPattern(path,con).getDesignPattern("Mediator");
                setVisible(false);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        defaultsButton.addActionListener(e->{
            try {
                new DesignPattern(path,null).getDesignPattern("Mediator");
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
