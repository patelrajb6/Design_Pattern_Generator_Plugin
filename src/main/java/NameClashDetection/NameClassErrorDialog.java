package NameClashDetection;

import PlugInViews.DesignPanel;
import com.intellij.ui.content.ContentManager;

import javax.swing.*;
import java.util.List;

public class NameClassErrorDialog extends JFrame {
    private JPanel rootpanel;
    private JButton OKButton;
    private JLabel label;
    private JLabel errorfiles;
    private JTextArea placeholderTextArea;
    public NameClassErrorDialog(List<String> errorNames){
        setLocationRelativeTo(null);
        setContentPane(rootpanel);
        errorfiles.setText("testing");
        setVisible(true);
        pack();
        OKButton.addActionListener(e->{
            setVisible(false);

        });
    }
}
