package PlugInViews;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesignWindow implements ToolWindowFactory {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        DesignPanel tool= new DesignPanel(toolWindow, project);     //new instance of DesignPanel
        ContentFactory contents= ContentFactory.SERVICE.getInstance();  //get the current contents of the GUI
        Content content = contents.createContent(tool.getContent(), "", true); //creates the Panel
        toolWindow.getContentManager().addContent(content); //adding the content to the tool window
        logger.info(getClass().toString()+"::createToolWindowContent success");
    }
}
