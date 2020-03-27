package DesignPatterns;

import NameClashDetection.NameClassErrorDialog;
import NameClashDetection.clashDetector;
import PlugInViews.ConfirmationDialog;
import PlugInViews.DesignPanel;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
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
    List<String>duplicateFiles= new ArrayList<>();
    List<String>generatedFiles= new ArrayList<>();
    String dirPath;
    String absolutePath;
    Project currentProject= DesignPanel.currentProject;
    abstract  void createClass(String name,String path)throws IOException;
    abstract public void GenerateCode(String path)throws IOException;
    void generateFile (String syntax, String fileName, String path) {
        try {
            File GeneratedCode= new File(path+"/java"); //creates a directory in project "GeneratedCode
            GeneratedCode.mkdir();
            dirPath=GeneratedCode.getPath()+'/';;
            if(fileName.length()==0)      //if the user doesnt type anything
                return ;
            absolutePath=dirPath+fileName + ".java";
            File file = new File(absolutePath);
            if (file.createNewFile()) {
                generatedFiles.add(absolutePath);
                logger.info("File is created!");
            } else {
                duplicateFiles.add(fileName+".java");
                logger.debug("File already exists.");
            }
            FileWriter writer = new FileWriter(file);
            writer.write(syntax);
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("File generation failed");
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
        clashDetector detector= new clashDetector(currentProject);
        detector.getPsifiles();
        File fileLocater;
        File dir= new File(dirPath);
        System.out.println(dir.isDirectory());
        if(duplicateFiles.size()==0){

            new ConfirmationDialog();
        }
        else{
            new NameClassErrorDialog(duplicateFiles);
            for (String file: generatedFiles){
                fileLocater= new File(absolutePath);
                System.out.println(fileLocater.getName());
                System.out.println(fileLocater.delete());
            }
            generatedFiles.clear();
            duplicateFiles.clear();
        }
    }
}
