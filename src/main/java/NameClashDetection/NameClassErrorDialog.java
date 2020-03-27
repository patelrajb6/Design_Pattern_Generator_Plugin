package NameClashDetection;
import javax.swing.*;
import java.util.List;

public class NameClassErrorDialog extends JFrame {
    private JPanel rootpanel;
    private JButton OKButton;
    private JLabel label;
    private JList list1;
    private JLabel errorfiles;
    private JTextArea placeholderTextArea;
    public NameClassErrorDialog(List<String> errorNames){
        setLocationRelativeTo(null);
        setContentPane(rootpanel);
       DefaultListModel<String> listModel=new DefaultListModel();
        for(String file:errorNames){
            listModel.addElement(file);
        }
        list1.setModel(listModel);
        setVisible(true);
        pack();
        OKButton.addActionListener(e->{
            setVisible(false);
        });
    }
}
