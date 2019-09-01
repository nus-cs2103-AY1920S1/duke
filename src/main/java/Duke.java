import java.util.ArrayList;

/**
 * <h1>Duke</h1>
 * The Duke program allows users to manage and organise their to-do, deadlines, and events
 * all in one application. Users can add their tasks, marking them as done, list the tasks
 * they have, and even delete them after they are done.
 */
public class Duke { // handles all input and output
    private DukeData _myData;

    public Duke() {
        this._myData = new DukeData();
    }

    public Duke(String filePath) {
        this._myData = new DukeData(filePath);
    }

    public void run() {
        Parser parseIt = new Parser(this);
        parseIt.parse();
    }

    public ArrayList<Task> getTaskList() {
        return this._myData.load().getList();
    }

    public DukeData getData() {
        return this._myData;
    }

    /**
     * This main method handles all input and outputs which the Duke program uses.
     * @param args Unused.
     */
    public static void main(String[] args) { // handles all input and output
        Duke theDuke = new Duke();
        theDuke.run();
    }
}
