package PlugInViews;

import ConfigGenerator.MediatorConfig;
import ConfigGenerator.TemplateConfig;
import DesignPatterns.DesignPattern;
import com.typesafe.config.Config;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TemplateView extends JFrame {
    private JPanel rootPanel;
    private JButton defaultsButton;
    private JButton okButton;
    private JTextField ConcreteClassesText;
    private JTextField AbstractMethodsText;
    private JTextField TemplateMethod;
    private JTextField AbstractClassText;
    private JLabel title;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    public TemplateView(String path) throws HeadlessException {
        setLocationRelativeTo(null);
        okButton.addActionListener(e->{
            try {
                Config con = TemplateConfig.UserInput(AbstractClassText.getText(), TemplateMethod.getText(),AbstractMethodsText.getText(),ConcreteClassesText.getText());
                new DesignPattern(path,con).getDesignPattern("Template");
                setVisible(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        defaultsButton.addActionListener(e->{
            try {
                new DesignPattern(path,null).getDesignPattern("Template");
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
