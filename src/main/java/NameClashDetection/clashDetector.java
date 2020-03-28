package NameClashDetection;

import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.pkcs11.Secmod;

import java.util.ArrayList;
import java.util.List;

public class clashDetector {
    Logger logger= LoggerFactory.getLogger(getClass());
    private final Project project;
    public clashDetector( Project project){
        this.project=project;
    }
    public void getPsifiles(){
        List<VirtualFile> allfiles=getVirtualFiles();
        for(VirtualFile v : allfiles)
        {
            PsiFile p= PsiManager.getInstance(project).findFile(v);
            if(p!=null)
            {
                System.out.println("file parent: "+p.getParent().getName());
                System.out.println("psi file data:--- "+p);
            }
        }
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
}
