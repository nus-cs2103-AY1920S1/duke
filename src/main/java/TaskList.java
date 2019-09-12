package seedu.duke;

import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeDoneEmptyListException;
import seedu.duke.exception.DukeIndexOutOfBoundException;
import seedu.duke.comparator.NameComparator;
import seedu.duke.comparator.DateComparator;
import java.util.ArrayList;

/**
 * Class containing the list of Tasks, and methods to modify it.
 * @author Lim Daekoon
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with the content passed in.
     * @param taskList Array containing list of Tasks to be loaded in.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Marks a task with the specified index as done.
     * @param parser Parser object containing a valid "done" command.
     * @throws DukeDoneEmptyListException If the list is empty and there is nothing to mark as done.
     * @throws DukeIndexOutOfBoundException If index specified in the command is out of bound.
     */
    public void markAsDone(Parser parser) throws DukeException {

        int index = parser.getIndex();

        // EXCEPTION: WHEN INDEX IS OUT OF RANGE
        if (index < 1 || index > taskList.size()) {

            String errorMessage;

            if (taskList.size() == 0) {
                throw new DukeDoneEmptyListException();
            } else {
                throw new DukeIndexOutOfBoundException(index, taskList.size());
            }
        }

        taskList.get(index - 1).markAsDone();

        String[] outputs = new String[2];
        outputs[0] = "Nice! I've marked this task as done: ";
        outputs[1] = "  " + taskList.get(index - 1).toString();

        Ui.printBlock(outputs);
    }

    /**
     * Adds a new Todo object.
     * @param parser Parser object containing a valid "todo" command.
     */
    public void addTodo(Parser parser) {
        Todo newTodo = new Todo(parser.getDescription());
        taskList.add(newTodo);

        String[] outputs = new String[3];
        outputs[0] = "Got it. I've added this task:";
        outputs[1] = "  " + newTodo.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", taskList.size());

        Ui.printBlock(outputs);
    }

    /**
     * Lists the existing tasks.
     */
    public void list() {
        if (taskList.size() == 0) {
            Ui.printBlock("There is nothing to list - the todo list is empty.");
            return;
        }
        Ui.printBlock(toStringArray());
    }

    ArrayList<Task> getTaskArray() {
        return this.taskList;
    }

    /**
     * Adds a new Deadline Task to the TaskList.
     * @param parser Parser object containing a "deadline" command.
     * @throws DukeException If the command is invalid.
     */
    public void addDeadline(Parser parser) throws DukeException {

        Deadline newDeadline = new Deadline(parser.getDescription(), parser.getTime());
        taskList.add(newDeadline);

        String[] outputs = new String[3];
        outputs[0] = "Got it. I've added this task:";
        outputs[1] = "  " + newDeadline.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", taskList.size());

        Ui.printBlock(outputs);
    }

    /**
     * Adds a new Event Task to the TaskList.
     * @param parser Parser object containing a "deadline" command.
     * @throws DukeException If the command is invalid.
     */
    public void addEvent(Parser parser) throws DukeException {

        Event newEvent = new Event(parser.getDescription(), parser.getTime());
        taskList.add(newEvent);

        String[] outputs = new String[3];
        outputs[0] = "Got it. I've added this task:";
        outputs[1] = "  " + newEvent.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", taskList.size());

        Ui.printBlock(outputs);
    }

    /**
     * Deletes the task with the specified index.
     * @param parser Parser object containing a valid "delete" command.
     * @throws DukeDoneEmptyListException If the list is empty and there is nothing to delete.
     * @throws DukeIndexOutOfBoundException If index specified in the command is out of bound.
     */
    public void deleteTask(Parser parser) throws DukeException {

        int index = parser.getIndex();

        // EXCEPTION: WHEN INDEX IS OUT OF RANGE
        if (index < 1 || index > taskList.size()) {
            String errorMessage;
            if (taskList.size() == 0) {
                throw new DukeDoneEmptyListException();
            } else {
                throw new DukeIndexOutOfBoundException(index, taskList.size());
            }
        }

        Task oldTask = taskList.get(index - 1);
        taskList.remove(index - 1);

        String[] outputs = new String[3];
        outputs[0] = "Noted. I've removed this task:";
        outputs[1] = "  " + oldTask.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", taskList.size());

        Ui.printBlock(outputs);
    }

    /**
     * Finds the task that matches the specified search term, and lists them out.
     * @param parser Parser object containing a valid "find" command.
     */
    public void find(Parser parser) {
        ArrayList<Task> matches = new ArrayList<Task>();
        Task currentTask;
        String regex = String.format("(.*)%s(.*)", parser.getSearchTerm());
        for (int z = 0; z < taskList.size(); z++) {
            currentTask = taskList.get(z);
            if (currentTask.getTaskName().matches(regex)) {
                matches.add(currentTask);
            }
        }

        if (matches.size() < 1) {
            Ui.printBlock("There were no matching tasks in your list.");
        } else {
            String[] stringArray = new String[matches.size()];
            for (int z = 0; z < matches.size(); z++) {
                stringArray[z] = String.format("%3d." + matches.get(z).toString(), z + 1);
            }
            Ui.printBlock(stringArray);
        }
    }

    /**
     * Sorts the task list according to the settings specified by the user's command.
     */
    public void sort(Parser parser) {
        switch (parser.getSortBy()) {
        case "name":
            this.taskList.sort(new NameComparator(parser.getSortOrder()));
            Ui.printBlock(String.format("Task list sorted by name in %s order", parser.getSortOrder()));
            break;

        case "date":
            this.taskList.sort(new DateComparator(parser.getSortOrder()));
            Ui.printBlock(String.format("Task list sorted by date in %s order", parser.getSortOrder()));
            break;

        default:
            Ui.printBlock("Unknown parameter, task list not sorted");
            return;
        }
    }

    // Converts taskList into a String Array
    private String[] toStringArray() {
        String[] stringArray = new String[taskList.size()];
        for (int z = 0; z < taskList.size(); z++) {
            stringArray[z] = String.format("%3d." + taskList.get(z).toString(), z + 1);
        }
        return stringArray;
    }
}

