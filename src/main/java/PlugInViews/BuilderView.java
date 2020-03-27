package PlugInViews;

import ConfigGenerator.BuilderConfig;
import DesignPatterns.DesignPattern;
import com.typesafe.config.Config;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BuilderView extends JFrame {
    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;
    private JTextField tf4;
    private JLabel Product;
    private JLabel Interface;
    private JLabel Parts;
    private JLabel BuildClasss;
    private JButton Defaults;
    private JButton Ok;
    private JLabel title;
    private JPanel rootPanel;

    public BuilderView(String path) throws HeadlessException {
        setLocationRelativeTo(null);
        Ok.addActionListener(e->{
            try {
                Config con =BuilderConfig.UserInput(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText());
                new DesignPattern(path,con).getDesignPattern("Builder");
                setVisible(false);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Defaults.addActionListener(e->{
            try {
                new DesignPattern(path,null).getDesignPattern("Builder");
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
