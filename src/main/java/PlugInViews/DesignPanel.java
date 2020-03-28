package PlugInViews;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;

public class DesignPanel extends JFrame {
    Logger logger = LoggerFactory.getLogger(getClass());
    public static Project currentProject;
    private JButton abstractFactoryButton;
    private JButton builderButton;
    private JButton chainOfResponsibilityButton;
    private JButton facadeButton;
    private JButton factoryButton;
    private JButton mediatorButton;
    private JButton templateButton;
    private JButton visitorButton;
    private HashMap<JButton,Runnable> ButtonStrings;
    private JPanel rootPanel;
    private JLabel title1;
    private JLabel title2;

    public DesignPanel(ToolWindow window, Project project){         // constructor which gets the path and also adds the listeners
        try{
            currentProject=project;
            String path=CreateAndGetPath(project);  //gets the path
            initHashmap(path);
            createHandler(path);
            logger.info(getClass().toString()+"::DesignPanel success");

        }catch (Exception e){
            logger.error(getClass().toString()+":: Constructor failed",e);
        }

    }
    public void initHashmap(String path){  // creates a hashmap from the button to runnable
        ButtonStrings= new HashMap<JButton, Runnable>();
        ButtonStrings.put(abstractFactoryButton,()->{
            new AbstractView(path);
        });
        ButtonStrings.put(builderButton,()->{
            new BuilderView(path);
        });
        ButtonStrings.put(chainOfResponsibilityButton,()->{
            new ChainView(path);
        });
        ButtonStrings.put(facadeButton,()->{
            new FacadeView(path);
        });
        ButtonStrings.put(factoryButton,()->{
            new FactoryView(path);
        });
        ButtonStrings.put(mediatorButton,()->{
            new MediatorView(path);
        });
        ButtonStrings.put(templateButton,()->{
             new TemplateView(path);
        });
        ButtonStrings.put(visitorButton,()->{
            new VisitorView(path);
        });
        logger.info(getClass().toString()+"::initHashmap success");
    }
    public void createHandler(String path){ //creates the actionlistener for each button
        ButtonStrings.forEach((key,value)->key.addActionListener(e-> value.run()));
        logger.info(getClass().toString()+"::createHandler success");

    }
    public String CreateAndGetPath(Project project) {
        String path=project.getBasePath()+"/src/main/";
        new File(path).mkdirs();
        logger.info(getClass().toString()+"::CreateAndPath success");
        return path;    //stores all the generated files there
    }
    public JPanel getContent(){
        return rootPanel;       //returns the main Panel
    }
}
