package NameClashDetection;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class ClashDetector {
    Logger logger= LoggerFactory.getLogger(getClass());
    //getting the project
    private final Project project;
    //list of psifiles
    private List<PsiFile> psiJavaFiles;
    //constructor
    public ClashDetector(Project project){
        this.project=project;
        psiJavaFiles= new ArrayList<>();    //initializes the list
        getPsiJavafiles();      //gets the psi files
    }
   /*
   *  function gets the java psi files
   * from the virtual files if exists
   * */
    private void getPsiJavafiles(){
        List<VirtualFile> allfiles=getVirtualFiles();
        for(VirtualFile v : allfiles)
        {
            PsiFile file= PsiManager.getInstance(project).findFile(v);
            if(file!=null && file.getFileType().getDescription().equals("Java"))
            {
                psiJavaFiles.add(file);
                System.out.println("file parent: "+file.getParent().getName());
                System.out.println("psi file type:--- "+file.getFileType().getDescription());
            }
        }
      ;
    }
    /* function gets the virtual files for the source roots from all modules
    *  */
    private List<VirtualFile> getVirtualFiles(){        //getting all the virtual files in the source roots of the modules..
        List<VirtualFile> allfiles = new ArrayList<>();
        try{
            //gts the source folder from all the modules.
            VirtualFile[] moduleSourceRoot = ProjectRootManager.getInstance(this.project).getContentSourceRoots();
            for(VirtualFile vf: moduleSourceRoot)
            {
                allfiles.addAll(VfsUtil.collectChildrenRecursively(vf));
            }
        }catch (Exception e){
            logger.error(getClass().toString()+":: getVirtualFile Error", e);
        }
        return allfiles;
    }

    /* The only public function which is used for checking if the file has type clashes
    * returns a pair of  boolean and psifile or null(if not found)*/
    public Pair<Boolean,PsiFile> ClashChecker(String filename){
        for( PsiFile file : psiJavaFiles){
            if(file.getName().equals(filename)){        //if true return the true,psifile
                Pair<Boolean,PsiFile>pair =new Pair<Boolean, PsiFile>(true,file);
                return pair;
            }
        }
        return new Pair<Boolean, PsiFile>(false, null);     //if not found  return false, null
    }
}
