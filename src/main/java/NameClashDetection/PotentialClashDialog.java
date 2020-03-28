package NameClashDetection;

import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiFile;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class PotentialClashDialog extends JFrame{
    private JPanel rootpanel;
    private JList list1;
    private JList list2;
    private JButton OKButton;
    private JLabel oklabel;
    private JButton cancelButton;

    public PotentialClashDialog(List<Pair<PsiFile,File>>warningfiles){

        setTitle("Potential warnings");
        setLocationRelativeTo(null);
        setContentPane(rootpanel);
        DefaultListModel<String> listfileModel=new DefaultListModel();
        DefaultListModel<String> listLocationModel=new DefaultListModel();

        for(Pair<PsiFile,File> file:warningfiles){
            listfileModel.addElement(file.second.getName());
            listLocationModel.addElement(file.first.getVirtualFile().getPath());
        }

        list1.setModel(listfileModel);
        list2.setModel(listLocationModel);
        setVisible(true);
        pack();
        cancelButton.addActionListener(e->{
            for(Pair<PsiFile,File> file:warningfiles){
                System.out.println(file.second.delete());
            }
            setVisible(false);
        });
        OKButton.addActionListener(e->{
            setVisible(false);
        });
    }
}
