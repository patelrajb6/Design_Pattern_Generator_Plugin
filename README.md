## Raj Patel  
# Project Overview:  
## Task: Build Plugin for the IntelliJ  
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
       ├── build.gradle  
       ├── gradle  
       ├── gradlew  
       ├── gradlew.bat  
       ├── settings.gradle  
       └── src  
           ├── main  
           │   ├── java  
           │   │   ├── ConfigGenerator   #package which got files which generates user choice of Config  
           │   │   ├── DesignPatterns    #package imported from previous hw1 (needed to be modified)  
           │   │   └── PlugInViews       #package contains all the files need to create GUI and its binding  
           │   └── resources  
           │       └── META-INF  
           └── test  
               ├── java  
               │   └── DesignPatterns   #Testing the Default and User Generated behaviour  
               └── resources  
### Tools used:
       Java as programming language to code  
       Slf4j logger for logging/debugging  
       Typesafe Configuration for Configuration files  
       JUnit 4 for unit testing  
       Java Swing for building GUI elements  
       Git for version control. 
 
   

        