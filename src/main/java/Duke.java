import java.util.ArrayList;

/**
 * <h1>Duke</h1>
 * The Duke program allows users to manage and organise their to-do, deadlines, and events
 * all in one application. Users can add their tasks, marking them as done, list the tasks
 * they have, and even delete them after they are done.
 */
public class Duke { // handles all input and output
    private DukeData _myData;

    /**
     * Creates a Duke program.
     */
    public Duke() {
        this._myData = new DukeData();
    }

    /**
     * Creates a Duke program with filePath as the path to save Duke's data.
     * @param filePath the path to save the Duke's data from user input.
     */
    public Duke(String filePath) {
        this._myData = new DukeData(filePath);
    }

    /**
     * This method runs the Duke program.
     */
    public void run() {
        Parser parseIt = new Parser(this);
        parseIt.parse();
    }

    /**
     * This method allows for retrieval of the user's TaskList that has been entered into Duke.
     * @return ArrayList of Task objects that contains the users' tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this._myData.load().getList();
    }

    /**
     * The getData() method retrieves the data that Duke has stored fdr the user.
     * @return DukeData representation of the data that has been stored.
     */
    public DukeData getData() {
        return this._myData;
    }

    /**
     * This is the main method that initiates the running of the Duke program.
     * @param args Unused.
     */
    public static void main(String[] args) { // handles all input and output
        Duke theDuke = new Duke("/Users/StudyBuddy/Desktop/CS2103/iP/duke/src/main/data");
        theDuke.run();
    }
}
