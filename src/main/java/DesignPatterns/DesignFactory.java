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
        /*
        * function generates the files
        */
        try {
            dirPath=createDir(path);  // creates directory to store the generated files.
            absolutePath=dirPath+fileName + ".java";
            File file = new File(absolutePath);     //creating new file object
            createFile(file,syntax); // function which makes the file and writes to it.
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("File generation failed");
        }

    }
    /* createDir
    * created a directory generatedCode to store the generated files
    */
    private String createDir(String path){
        File dir= new File(path+"/GeneratedCode");
        dir.mkdir();
        return dir.getPath()+"/";
    }
    /* create file
    * this function creates the file and then writes to it
    * apart from creating file it also checks for potential clash files in the source roots
    * if file found to be in any other  module source then add it to the clashfiles
    * where they are stored as pair of file and psifile */
    private void createFile(File file,String syntax){
        try{
            if (file.createNewFile()) {
                generatedFiles.add(absolutePath);       //add the generated files for sanity checking
                Pair<Boolean,PsiFile>result=detector.ClashChecker(file.getName());  //sending the file to clashdetector for checking
                if(result.first){   //if  found
                    clashFiles.add(new Pair<PsiFile, File>(result.second,file));// add it to the clashfiles
                }

                writetoFile(file, syntax);  //writing to the file.
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



    /*CheckRepeatedFiles is ran at the end after files have been created for the given pattern
    *  according to the  different conditions different dialog boxes are opened.
    * */
    public void CheckRepeatedFiles(){
        File fileLocater;
        //if there are duplicate files then opens the dialog and lets user know for duplicate files
        if(duplicateFiles.size()!=0){
            new NameClassErrorDialog(duplicateFiles);
            for (String file: generatedFiles){
                fileLocater= new File(file);
                fileLocater.delete();
                }
        }
        //if the clash files has files then open the clash dialog and display the warnings
        else if( clashFiles.size()!=0){
            new PotentialClashDialog(clashFiles);
        }
        //all went well than confirmation
        else
            new ConfirmationDialog();
    }
}
