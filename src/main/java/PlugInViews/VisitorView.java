package PlugInViews;

import ConfigGenerator.TemplateConfig;
import ConfigGenerator.VisitorConfig;
import DesignPatterns.DesignPattern;
import com.typesafe.config.Config;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VisitorView extends JFrame{
    private JPanel rootPanel;
    private JButton defaultsButton;
    private JButton okButton;
    private JTextField ConcreteVisitorView;
    private JTextField AbstractVisistorView;
    private JTextField ConcreteElements;
    private JTextField AbstractElement;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel title;

    public VisitorView(String path) throws HeadlessException {
        setTitle("Visitor");
        setLocationRelativeTo(null);
        okButton.addActionListener(e->{
            try {
                Config con = VisitorConfig.UserInput(AbstractElement.getText(), ConcreteElements.getText(),AbstractVisistorView.getText(),ConcreteVisitorView.getText());
                new DesignPattern(path,con).getDesignPattern("Visitor");
                setVisible(false);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        defaultsButton.addActionListener(e->{
            try {
                new DesignPattern(path,null).getDesignPattern("Visitor");
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
