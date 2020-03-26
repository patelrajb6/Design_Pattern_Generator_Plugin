package DesignPatterns;

import NameClashDetection.NameClassErrorDialog;
import PlugInViews.ConfirmationDialog;
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
    String dirPath;
    abstract  void createClass(String name,String path)throws IOException;
    abstract public void GenerateCode(String path)throws IOException;
    void generateFile (String syntax, String fileName, String path) {
        try {
            dirPath=path;
            if(fileName.length()==0)      //if the user doesnt type anything
                return ;
            File file = new File(path+fileName + ".java");
            if (file.createNewFile()) {
                logger.info("File is created!");
            } else {
                duplicateFiles.add(fileName);
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
        File dir= new File(dirPath);
        System.out.println(dir.isDirectory());
        for (File f: dir.listFiles()){
            System.out.println(f.getName());
        }
        if(duplicateFiles.size()==0){
            new ConfirmationDialog();
        }
        else{
            new NameClassErrorDialog(duplicateFiles);

        }


    }

}
