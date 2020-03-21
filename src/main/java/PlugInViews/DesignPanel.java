package PlugInViews;

import DesignPatterns.DesignPattern;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DesignPanel extends JFrame {
    Logger logger = LoggerFactory.getLogger(getClass());
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
//            try {
//                new DesignPattern(path,null).getDesignPattern("AbstractFactory");
//                new ConfirmationDialog();
//            } catch (IOException ex) {
//                logger.error(getClass().toString()+"::abstractFactoryButton failed",ex);
//            }

            new AbstractView(path);
        });
        ButtonStrings.put(builderButton,()->{
//            try {
//                new DesignPattern(path,null).getDesignPattern("Builder");
//                new ConfirmationDialog();
//            } catch (IOException ex) {
//                logger.error(getClass().toString()+"::BuilderButton failed",ex);
//            }
            new BuilderView(path);
        });
        ButtonStrings.put(chainOfResponsibilityButton,()->{
//            try {
//                new DesignPattern(path,null).getDesignPattern("Chain");
//                new ConfirmationDialog();
//            } catch (IOException ex) {
//                logger.error(getClass().toString()+"::chainButton failed",ex);
//            }
            new ChainView(path);
        });
        ButtonStrings.put(facadeButton,()->{
//            try {
//                new DesignPattern(path,null).getDesignPattern("Facade");
//                new ConfirmationDialog();
//            } catch (IOException ex) {
//                logger.error(getClass().toString()+"::facadeButton failed",ex);
//            }
            new FacadeView(path);
        });
        ButtonStrings.put(factoryButton,()->{
//            try {
//                new DesignPattern(path,null).getDesignPattern("Factory");
//                new ConfirmationDialog();
//            } catch (IOException ex) {
//                logger.error(getClass().toString()+"::facadeButton failed",ex);
//            }
            new FactoryView(path);
        });
        ButtonStrings.put(mediatorButton,()->{
//            try {
//                new DesignPattern(path,null).getDesignPattern("Mediator");
//                new ConfirmationDialog();
//            } catch (IOException ex) {
//                logger.error(getClass().toString()+"::mediatorButton failed",ex);
//            }
            new MediatorView(path);
        });
        ButtonStrings.put(templateButton,()->{
//            try {
//                new DesignPattern(path,null).getDesignPattern("Template");
//                new ConfirmationDialog();
//            } catch (IOException ex) {
//                logger.error(getClass().toString()+"::templateButton failed",ex);
//            }
             new TemplateView(path);
        });
        ButtonStrings.put(visitorButton,()->{
//            try {
//                new DesignPattern(path,null).getDesignPattern("Visitor");
//                new ConfirmationDialog();
//            } catch (IOException ex) {
//                logger.error(getClass().toString()+"::visitorButton failed",ex);
//            }
            new VisitorView(path);
        });
        logger.info(getClass().toString()+"::initHashmap success");
    }
    public void createHandler(String path){ //creates the actionlistener for each button
        ButtonStrings.forEach((key,value)->key.addActionListener(e-> value.run()));
        logger.info(getClass().toString()+"::createHandler success");

    }
    public String CreateAndGetPath(Project project) {
        String path= project.getBasePath()+'/';
        System.out.println(path);
        File GeneratedCode= new File(path+"/GeneratedCode"); //creates a directory in project "GeneratedCode
        GeneratedCode.mkdir();
        path= GeneratedCode.getPath()+'/';
        logger.info(getClass().toString()+"::CreateAndPath success");
        return path;    //stores all the generated files there
    }
    public JPanel getContent(){
        return rootPanel;       //returns the main Panel
    }
}
