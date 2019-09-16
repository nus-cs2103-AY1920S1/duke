import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class where the main method is located. Takes in user inputs, and processes the results of Duke's responses.
 */
public class Duke {
    // Class logic components
    private DukeSaveLoad dukeSaveLoad;
    private TaskList tasks;
    private NoteList notes;
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
        tasks = dukeSaveLoad.attemptLoadTaskList();
        notes = dukeSaveLoad.attemptLoadNoteList();
    }

    /**
     * Returns Duke's reponse to the User. This method will change Duke's state.
     * 
     * @param inputString The User input string.
     * @return Duke's response to the user.
     * 
     * @throws FileNotFoundException If a save file does not exist and cannot be created, or cannot be opened.
     * @throws IOException When an IOException occurs.
     * @throws SecurityException If a security manager exists and its checkWrite method denies write access to the file.
     */
    public String getResponse(String inputString) throws FileNotFoundException, IOException, SecurityException {
        try {
            DukeReply dukeReply = UserInputProcessor.processUserInput(inputString, tasks, notes);

            if (dukeReply.shouldSaveTaskList) {
                dukeSaveLoad.attemptSaveTaskList(tasks);
            }

            if (dukeReply.shouldSaveNoteList) {
                dukeSaveLoad.attemptSaveNoteList(notes);
            }

            systemShouldShutdown = dukeReply.shouldExitLoop;

            return dukeReply.dukeReplyString;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns Duke's hello message.
     * 
     * @return Duke's hello message.
     */
    public String sayHi() {
        return DukeUi.GREET_HELLO;
    }

    /**
     * Returns whether the system should shutdown.
     * 
     * @return Whether the system should shutdown.
     */
    public boolean systemShouldShutdown() {
        return systemShouldShutdown;
    }
}
