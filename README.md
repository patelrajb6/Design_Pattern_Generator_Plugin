## Raj Patel  
# Project Overview:  
## Task: Build Plugin for the IntelliJ with Name clash detection
## Whats new:
This version of the Design Pattern Generator includes:  

1. Duplicate file checking for the folder the files are created in. 
2. Possible name clash checking for the files being created.
     
     The files being generated are checked if there exist a file with same name <br> in any of the source roots of the project and the filetype is java.
     Using projectRootManager to get the sourceroots and recursively getting all virtual files<br>
     and then processing those virtual files to get psifiles. Using PsiFile getting the java files.
     
     Method ClashChecker checks the file if it clashes or not. It also returns the PsiFile for which it clashes.<br>
     This is then displayed to the user as potential clash. This way the user knows what to do with it.
     
  
             
       
### Input:  
      User of this Plugin can choose from the following eight Design Pattern
        AbstractFactory  
        Builder  
        Chain  
        Facade  
        Factory  
        Mediator  
        Template  
        Visitor  
### Output:  
       User's choice of Design Pattern with either default values or thier choice.  
       All the files are generated in /GeneratedCode. 
### Usage :
           -> on Commandline  
             Gradle clean  
             Gradle build (--info)  #builds the project and also runs the tests  
             Gradle test (--info)   #runs the tests  
             Gradle runIde (--info)    #runs the Ide  
           ->Start the project and look for "Design Pattern Generator Dock on the extreme Right Dockable windows  
             Click and it will open the window where user presses the button of Design Pattern  
             this opens a new window where user can decide to enter his/her choices or press Defaults to generate Defaults.  
    Results are stored in   /GeneratedCode directory   

### PlugIn file tree:  
       DePaCoG_PlugIn  
       ├── README.md  
       ├── build  
       ├── build.gradle  #build setting for this module/project
       ├── gradle  
       ├── gradlew  
       ├── gradlew.bat  
       ├── settings.gradle  
       └── src  
         ├── main
         │   ├── java
         │   │   ├── ConfigGenerator  # package for user generated configs
         │   │   ├── DesignPatterns  # package for design pattern generator code
         │   │   ├── NameClashDetection # package needed for checking clashes
         │   │   └── PlugInViews # package for views and dialogs for the the designpatterns
         │   └── resources
         └── test
             ├── java
             │   └── DesignPatterns
             └── resources
 
### Tools used:
       Java as programming language to code  
       Slf4j logger for logging/debugging  
       Typesafe Configuration for Configuration files  
       JUnit 4 for unit testing  
       Java Swing for building GUI elements  
       Git for version control. 
 
   

        