package duke;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskWithDate;
import duke.task.Todo;
import duke.ui.ErrorMsg;
import duke.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;

import static duke.ui.ErrorMsg.INVALID_DATE_FORMAT;

public class TaskList extends ArrayList<Task> {

    /**
     * Old implementation without the use of Commands.
     * Currently in use by Storage to import tasks from text file.
     *
     * @param ui ui object
     * @param taskName name of task e.g. todo, event
     * @param line line (e.g. event dancing /at date)
     * @throws DukeException generic exception with error message
     */
    public void insertByCommand(Ui ui, String taskName, String line) throws DukeException {
        String data = line.replaceFirst("^.*?\\s","");
        //If no change, then it's either empty, or invalid command
        if (line.equals(data)) {
            if (line.equals("todo") || line.equals("event") || line.equals("deadline")) {
                data = "";
            } else {
                throw new DukeException(ErrorMsg.UNKNOWN_COMMAND);
            }
        }

        String[] splitData;
        try {
            switch (taskName) {
            case "todo":
                if (Task.validateData(data, "todo")) {
                    insertNewTask(ui, new Todo(data));
                }
                break;
            case "event":
                splitData = TaskWithDate.extractDataFromLine(data, " /at ");
                if (TaskWithDate.validateData(splitData, "event")) {
                    insertNewTask(ui, new Event(splitData[0], splitData[1]));
                }
                break;
            case "deadline":
                splitData = TaskWithDate.extractDataFromLine(data, " /by ");
                if (TaskWithDate.validateData(splitData, "deadline")) {
                    insertNewTask(ui, new Deadline(splitData[0], splitData[1]));
                }
                break;
            default:
                //throw new DukeException("Invalid command.");
                throw new DukeException(ErrorMsg.UNKNOWN_COMMAND);
            }
        } catch (ParseException e) {
            throw new DukeException(INVALID_DATE_FORMAT);
        }
    }

    /**
     * Insert a new task into the task list.
     * @param ui ui object
     * @param taskToInsert task to insert
     * @throws DukeException generic exception with error message
     */
    public void insertNewTask(Ui ui, Task taskToInsert) {
        assert taskToInsert != null;
        this.add(taskToInsert);
        ui.println("Got it. I've added this task: ");
        ui.println("  " + taskToInsert.toString());
        ui.println("Now you have " + this.size() + " tasks in the list.");
    }
}
