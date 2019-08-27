import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class where the main method is located. Takes in user inputs, and processes the results of Duke's responses.
 */
public class Duke {
    private DukeSaveLoad dukeSaveLoad;
    private TaskList tasks;

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

    private void run() throws FileNotFoundException, IOException, SecurityException {
        Scanner sc = new Scanner(System.in);
        PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        printStream.print(DukeTextFormatter.makeFormattedText(DukeUi.GREET_HELLO));

        while (true) { //Interaction loop with user. This will persist until shutdown.
            String userInput = sc.nextLine();
            
            try {
                DukeReply dukeReply = UserInputProcessor.processUserInput(userInput, tasks);
            
                printStream.print(dukeReply.dukeReplyString);

                if(dukeReply.shouldSave) {
                    dukeSaveLoad.attemptSave(tasks);
                }

                if(dukeReply.shouldExitLoop) {
                    break;
                }
            } catch (DukeException e) {
                printStream.print(e.getMessage());
            }
        }

        sc.close();
    }

    /**
     * Creates a new instance of <code>Duke</code> and calls its <code>run</code> method.
     * @param args A <code>String</code> array containing command line arguments
     */
    public static void main (String [] args) {
        try{
            new Duke().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
