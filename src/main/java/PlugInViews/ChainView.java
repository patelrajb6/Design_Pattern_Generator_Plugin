package PlugInViews;

import ConfigGenerator.BuilderConfig;
import ConfigGenerator.ChainConfig;
import DesignPatterns.DesignPattern;
import com.typesafe.config.Config;

import javax.swing.*;
import java.io.IOException;

public class ChainView extends JFrame {
    private JButton defaultButton;
    private JButton okButton;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel title;
    private JLabel handler;
    private JLabel Reciever;
    private JPanel rootPanel;

    public ChainView(String path) {
        setLocationRelativeTo(null);
        okButton.addActionListener(e->{
            try {
                Config con = ChainConfig.UserInput(textField2.getText(), textField1.getText());
                new DesignPattern(path,con).getDesignPattern("Chain");
                setVisible(false);
                new ConfirmationDialog();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        defaultButton.addActionListener(e->{
            try {
                new DesignPattern(path,null).getDesignPattern("Chain");
                setVisible(false);
                new ConfirmationDialog();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        setContentPane(rootPanel);
        setVisible(true);
        pack();
    }
}
