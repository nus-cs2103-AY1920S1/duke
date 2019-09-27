import trackr.command.Command;
import trackr.command.HistoryCommand;
import trackr.command.UndoCommand;
import trackr.exception.TrackrException;
import trackr.history.HistoryTracker;
import trackr.parser.Parser;
import trackr.storage.Storage;
import trackr.task.Deadline;
import trackr.task.Event;
import trackr.task.Task;
import trackr.task.Todo;
import trackr.tasklist.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Trackr {

    /**
     * Deals with loading tasks from the file and saving tasks in the file.
     */
    private Storage storage;

    /**
     * Contains the task list.
     */
    private TaskList tasks;

    /**
     * Contains input and tasklist history.
     */
    private HistoryTracker history;

    /**
     * This is the class constructor assigning a filepath to the Duke object.
     */
    public Trackr() {
        storage = new Storage("trackr.txt");
        history = new HistoryTracker();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException ex) {
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
            if (!(c instanceof HistoryCommand)) {
                TaskList tasklistCopy = createTasklistCopy(tasks);
                history.addHistory(input, tasklistCopy);
            }
            return c.execute(tasks, storage, history);
        } catch (TrackrException e) {
            return e.getMessage();
        }
    }

    /**
     * Performs deep copy of the task list to create a new task list to save version history.
     * @param tasks List of tasks
     * @return TaskList New independent copy of TaskList
     */
    private TaskList createTasklistCopy(TaskList tasks) {
        TaskList t = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getType().equals("todo")) {
                Todo newTask = new Todo(currTask);
                t.add(newTask);
            } else if (tasks.get(i).getType().equals("event")) {
                Event newTask = new Event(currTask);
                t.add(newTask);
            } else {
                Deadline newTask = new Deadline(currTask);
                t.add(newTask);
            }
        }
        return t;
    }
}
