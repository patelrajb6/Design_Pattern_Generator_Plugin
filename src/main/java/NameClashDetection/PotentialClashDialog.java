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

        setLocationRelativeTo(null);
        setContentPane(rootpanel);
        DefaultListModel<String> listfileModel=new DefaultListModel();  //making default storage for the list elements
        DefaultListModel<String> listLocationModel=new DefaultListModel();  //stores location of particular clash file

        for(Pair<PsiFile,File> file:warningfiles){  //getting the file and the corresponding psiJavaFile
            listfileModel.addElement(file.second.getName());
            listLocationModel.addElement(file.first.getVirtualFile().getPath());
        }

        list1.setModel(listfileModel);
        list2.setModel(listLocationModel);
        setVisible(true);
        pack();
        /*
        *  clash files are already generated so we would need to clean it
        * if the user doesnt want those files
        * */
        cancelButton.addActionListener(e->{     //when the cancel button is pressed then do not generate file.
            for(Pair<PsiFile,File> file:warningfiles){
                file.second.delete();
            }
            setVisible(false);
        });
        OKButton.addActionListener(e->{
            setVisible(false);
        });
    }
}
