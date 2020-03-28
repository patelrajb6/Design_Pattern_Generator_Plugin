package NameClashDetection;

import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.pkcs11.Secmod;

import java.util.ArrayList;
import java.util.List;

public class ClashDetector {
    Logger logger= LoggerFactory.getLogger(getClass());
    private final Project project;
    private List<PsiFile> psiJavaFiles;
    public ClashDetector(Project project){
        this.project=project;
        psiJavaFiles= new ArrayList<>();
        getPsiJavafiles();
    }
    public void getPsiJavafiles(){
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
    private List<VirtualFile> getVirtualFiles(){        //getting all the virtual files in the source roots of the modules..
        List<VirtualFile> allfiles = new ArrayList<>();
        try{
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
    public Pair<Boolean,PsiFile> ClashChecker(String filename){
        for( PsiFile file : psiJavaFiles){
            System.out.println("ClashChecker: "+file.getName()+" "+filename);
            if(file.getName().equals(filename)){
                Pair<Boolean,PsiFile>pair =new Pair<Boolean, PsiFile>(true,file );
                return pair;
            }
        }
        return new Pair<Boolean, PsiFile>(false, null);
    }
}
