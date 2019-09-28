package duke.command;

import duke.exception.DukeException;
import duke.logic.Actions;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

/**
 * Adds a Task to the TaskList.
 */
public class AddCommand extends Command {

    private Actions action;

    /**
     * Constructs a new AddCommand object.
     *
     * @param input  input from the user.
     * @param action action to be performed.
     */
    public AddCommand(String input, Actions action) {
        super(input);
        this.action = action;
    }

    public TaskList detectAnomalies(Task currentTask, TaskList tasks) {
        TaskList clashedTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (currentTask.getDateTime().equals(task.getDateTime())) {
                clashedTasks.addTask(task);
            }
        }
        return clashedTasks;
    }

    /**
     * Executes the action to be performed.
     *
     * @param tasks   current list of tasks.
     * @param ui      Ui object.
     * @param storage Storage object to save and load files.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int num;
        String desc;
        Task task = null;
        String[] inputArr = input.split(" ");
        TaskList clashedTasks = null;
        switch (action) {
        case TODO:
            //trim so that cannot pass with just spaces
            desc = input.substring(4).trim();
            if (desc.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                task = new Todo(desc);
            }
            break;
        case DEADLINE:
            num = input.indexOf("/by");
            //length == 1 means only has 'deadline', and temp[1] equal /by means no desc as well
            if (inputArr.length == 1 || inputArr[1].equals("/by")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (num == -1) { //-1 means /by is not found
                throw new DukeException("☹ OOPS!!! Please type /by before inputting the deadline.");
            } else {
                desc = input.substring(9, num);
                //trim so that cannot pass with just spaces
                String by = input.substring(num + 3).trim();
                //no input time after /by
                if (by.equals("")) {
                    throw new DukeException("☹ OOPS!!! Please input a deadline after /by");
                } else {
                    task = new Deadline(desc, by);
                    clashedTasks = detectAnomalies(task, tasks);
                }
            }
            break;
        case EVENT:
            num = input.indexOf("/at");
            //length == 1 means only has 'event', and temp[1] equal /at means no desc as well
            if (inputArr.length == 1 || inputArr[1].equals("/at")) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            } else if (num == -1) { //-1 means /at is not found
                throw new DukeException("☹ OOPS!!! Please type /at before inputting the time.");
            } else {
                desc = input.substring(6, num);
                //trim so that cannot pass with just spaces
                String at = input.substring(num + 3).trim();
                //no input time after /at
                if (at.equals("")) {
                    throw new DukeException("☹ OOPS!!! Please input a time after /at");
                } else {
                    task = new Event(desc, at);
                    clashedTasks = detectAnomalies(task, tasks);
                }
            }
            break;
        default:
            break;
        }
        // if task is still null do nothing
        assert task != null : "Task is null, exceptions not handled.";
        tasks.addTask(task);
        if (clashedTasks == null || clashedTasks.getSize() == 0) {
            ui.setAddTaskResponse(task, tasks.getSize());
        } else {
            ui.setClashedTaskResponse(task, clashedTasks, tasks.getSize());
        }
        storage.save(tasks);
    }
}
