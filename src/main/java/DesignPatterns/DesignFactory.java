package DesignPatterns;

import NameClashDetection.PotentialClashDialog;
import PlugInViews.NameClassErrorDialog;
import NameClashDetection.ClashDetector;
import PlugInViews.ConfirmationDialog;
import PlugInViews.DesignPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiFile;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DesignFactory {
    Logger logger=LoggerFactory.getLogger(getClass());
    Config conf;
    List<String>duplicateFiles;
    List<String>generatedFiles;
    List<Pair<PsiFile,File>>clashFiles;
    String dirPath;
    String absolutePath;
    ClashDetector detector;

    Project currentProject;
    protected DesignFactory(){
        duplicateFiles= new ArrayList<>();
        generatedFiles= new ArrayList<>();
        clashFiles=new ArrayList<>();
        currentProject= DesignPanel.currentProject;
        detector= new ClashDetector(currentProject);

    }
    abstract  void createClass(String name,String path)throws IOException;
    abstract public void GenerateCode(String path)throws IOException;
    void generateFile (String syntax, String fileName, String path) {
        try {
            dirPath=createDir(path);
            absolutePath=dirPath+fileName + ".java";
            File file = new File(absolutePath);
            createFile(file,syntax);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("File generation failed");
        }

    }
    private String createDir(String path){
        File dir= new File(path+"/GeneratedCode");
        dir.mkdir();
        return dir.getPath()+"/";
    }
    private void createFile(File file,String syntax){
        try{
            if (file.createNewFile()) {
                generatedFiles.add(absolutePath);
                Pair<Boolean,PsiFile>result=detector.ClashChecker(file.getName());
                if(result.first){
                    clashFiles.add(new Pair<PsiFile, File>(result.second,file));
                }

                writetoFile(file, syntax);
                logger.info("File is created!");
            } else {
                duplicateFiles.add(file.getName());
                logger.debug("File already exists.");
            }
        }catch(Exception e){
            logger.error("file creation failed",e);
        }

    }
    private void writetoFile(File file, String syntax){
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(syntax);
            writer.close();
        }catch(Exception e){
            logger.error("filewriter failed",e);
        }
    }
    public Config getConfig(String filename)        //gets the config file's absolute path and returns the Config object
    {
        try {
            InputStream inStream = getClass().getResourceAsStream("/"+filename);
            if (inStream != null) {
                Reader reader = new InputStreamReader(inStream);
                // Load the configuration file
                conf = ConfigFactory.parseReader(reader);
                inStream.close();
                // Load the destination directory for designs being generated
                return conf;
            }
        } catch (IOException ex) {
            logger.error(String.format("Error in %s: getConfig", getClass()),ex);
        }
        return conf;
    }

    public void CheckRepeatedFiles(){
        File fileLocater;
        if( clashFiles.size()!=0){

            new PotentialClashDialog(clashFiles);
          //  clashFiles.clear();
        }
        else if(duplicateFiles.size()==0){

            new ConfirmationDialog();
        }
        else{

            new NameClassErrorDialog(duplicateFiles);
            for (String file: generatedFiles){
                fileLocater= new File(file);
                System.out.println(fileLocater.getName());
                System.out.println(fileLocater.delete());
            }
            generatedFiles.clear();
            duplicateFiles.clear();
        }
    }
}
