package NameClashDetection;

import com.intellij.psi.PsiFile;

import javax.swing.*;
import java.util.List;

public class PotentialClashDialog extends JFrame{
    private JPanel rootpanel;
    private JList list1;
    private JList list2;
    private JButton OKButton;

    public PotentialClashDialog(List<PsiFile>warningfiles){
        setTitle("Potential warnings");
        setLocationRelativeTo(null);
        setContentPane(rootpanel);
        DefaultListModel<String> listfileModel=new DefaultListModel();
        DefaultListModel<String> listLocationModel=new DefaultListModel();
        for(PsiFile file:warningfiles){
            listfileModel.addElement(file.getName());
            listLocationModel.addElement(file.getParent().getVirtualFile().getPath());
        }
        list1.setModel(listfileModel);
        list2.setModel(listLocationModel);
        setVisible(true);
        pack();
        OKButton.addActionListener(e->{
            setVisible(false);
        });
    }
}
