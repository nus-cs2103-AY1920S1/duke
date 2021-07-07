import javafx.application.Application;

//@@author jeffrylum-reused
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart3.md

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}

//@@author