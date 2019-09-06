//import java.util.Scanner;
//import java.io.PrintStream;
//import java.nio.charset.StandardCharsets;

import java.io.FileNotFoundException;
import java.io.IOException;

// JavaFX
/*
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
*/

/**
 * The class where the main method is located. Takes in user inputs, and processes the results of Duke's responses.
 */
public class Duke {
    // Class logic components
    private DukeSaveLoad dukeSaveLoad;
    private TaskList tasks;
    private boolean systemShouldShutdown = false;

    /**
     * Creates a new instance of Duke.
     * 
     * @throws NullPointerException When the <code>pathname</code> in DukeSaveLoad is <code>null</code>
     * @throws IOException If an IOException occured during reading file or pathnames in DukeSaveLoad
     * @throws FileNotFoundException If saveFile does not exist
     * @throws ClassNotFoundException When <code>TaskList</code> class cannot be found
     */
    public Duke() throws NullPointerException, IOException, FileNotFoundException, ClassNotFoundException {
        dukeSaveLoad = new DukeSaveLoad();
        tasks = dukeSaveLoad.attemptLoad();
    }

    public String getResponse(String inputString) throws FileNotFoundException, IOException, SecurityException {
        try {
            DukeReply dukeReply = UserInputProcessor.processUserInput(inputString, tasks);
        
            //printStream.print(dukeReply.dukeReplyString);

            if(dukeReply.shouldSave) {
                dukeSaveLoad.attemptSave(tasks);
            }

            systemShouldShutdown = dukeReply.shouldExitLoop;

            return dukeReply.dukeReplyString;
        } catch (DukeException e) {
            //printStream.print(e.getMessage());
            return e.getMessage();
        }
    }

    public String sayHi() {
        return DukeTextFormatter.makeFormattedText(DukeUi.GREET_HELLO);
    }

    public boolean systemShouldShutdown() {
        return systemShouldShutdown;
    }
}
