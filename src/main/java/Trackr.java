import trackr.command.Command;
import trackr.command.UndoCommand;
import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.parser.Parser;
import trackr.storage.Storage;
import trackr.tasklist.TaskList;

import java.io.FileNotFoundException;


public class Trackr {

    /**
     * Deals with loading tasks from the file and saving tasks in the file.
     */
    private Storage storage;

    /**
     * Contains the task list.
     */
    private TaskList tasks;

    private HistoryTracker history;

    /**
     * This is the class constructor assigning a filepath to the Duke object.
     */
    public Trackr() {
        storage = new Storage("C:/Users/Shawn Lee/Desktop/UNI FILES/Y2S1/CS2103T/duke/data/duke.txt");
        history = new HistoryTracker();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException ex) {
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves response from app logic based on user input.
     * @param input User input
     * @return String Appropriate response to user input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (!(c instanceof UndoCommand)) {
                TaskList tasklistCopy = createTasklistCopy(tasks);
                history.addHistory(input, tasklistCopy);
            }
            return c.execute(tasks, storage, history);
        } catch (TrackrException e) {
            return e.getMessage();
        }
    }

    private TaskList createTasklistCopy(TaskList tasks) {
        TaskList t = new TaskList();
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            t.getTasks().add(tasks.getTasks().get(i));
        }
        return t;
    }
}
