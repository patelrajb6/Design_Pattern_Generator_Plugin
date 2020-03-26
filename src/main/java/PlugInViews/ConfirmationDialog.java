package PlugInViews;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ConfirmationDialog extends JFrame  {
    private JPanel panel1;
    private JButton okButton;
    private JLabel confirm;
    public ConfirmationDialog(){
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        pack();

        okButton.addActionListener(e->setVisible(false));
    }
}
