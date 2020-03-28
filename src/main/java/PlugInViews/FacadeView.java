package PlugInViews;
import ConfigGenerator.FacadeConfig;
import DesignPatterns.DesignPattern;
import com.typesafe.config.Config;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FacadeView extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton okButton;
    private JButton defaultsButton;
    private JLabel title;
    private JLabel FacadeClass;
    private JPanel rootPanel;

    public FacadeView(String path) throws HeadlessException {
        setTitle("Facade");
        setLocationRelativeTo(null);
        okButton.addActionListener(e->{
            try {
                Config con = FacadeConfig.UserInput(textField1.getText(), textField2.getText(),textField3.getText());
                new DesignPattern(path,con).getDesignPattern("Facade");
                setVisible(false);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        defaultsButton.addActionListener(e->{
            try {
                new DesignPattern(path,null).getDesignPattern("Facade");
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
