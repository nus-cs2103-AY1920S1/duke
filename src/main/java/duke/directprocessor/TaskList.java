package duke.directprocessor;

import duke.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class holds the current task list input by the user. It is also in charge of save the task list into a file
 * when the exit command is given by the user.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * The constructor of the class to generate an empty task list.
     * Note that this constructor will be applied only when it fails to read from the file.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * The constructor of the class to give an existing task list.
     * This constructor is used when Storage class successfully read from the file.
     *
     * @param taskList The task list read from the input file.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * This methods return how many tasks are there in this task list.
     *
     * @return the number of tasks in the task list.
     */
    public int getTotalNumber() {
        return taskList.size();
    }

    /**
     * This method adds a given task t to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * This method delete the task at  a given position from the task list.
     * Do not worry about IndexOutOfBoundException, it will be caught in the duke.commands.DeleteCommand class.
     *
     * @param position The position of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int position) {
        Task toReturn = taskList.get(position - 1);
        taskList.remove(position - 1);
        return toReturn;
    }

    /**
     * This method set the task at a given position from the task list.
     * Do not worry about IndexOutOfBoundException, it will be caught in the duke.commands.FinishCommand class.
     *
     * @param position The position of the task to be set as finish.
     * @return The finished task.
     */
    public Task finishTask(int position) {
        taskList.get(position - 1).setAsFinish();
        return taskList.get(position - 1);
    }

    /**
     * Get all tasks whose task name contains the given message s in the form of a string array list.
     *
     * @param s The target string we want to find inside the task name.
     * @return All tasks whose task name matches the target string in the form of a string array list.
     */
    public ArrayList<String> listMatchTask(String s) {
        ArrayList<String> toReturn = new ArrayList<>();
        int frontier = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).match(s)) {
                toReturn.add((frontier) + "." + taskList.get(i).taskInfo());
                frontier++;
            }
        }
        return toReturn;
    }

    /**
     * This method list out all tasks in their task information as an string array list in the task list.
     *
     * @return The task information of all tasks in the form of a string array list.
     */
    public ArrayList<String> listAllTask() {
        ArrayList<String> toReturn = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            toReturn.add((i + 1) + "." + taskList.get(i).taskInfo());
        }
        return toReturn;
    }

    /**
     * This method adds a new possible slot to the target event.
     *
     * @param position The position of the target event in the task list.
     * @param s The string of the new slot.
     * @return The event task with added slot.
     * @throws DukeException If the position exceeds the size of the task list or the target task is not an event.
     */
    public Task addSlotToEvent(int position, String s) throws DukeException {
        if (position > getTotalNumber()) {
            throw new DukeException("There is no such task in the list, please specify a valid task.");
        }
        Task targetTask = taskList.get(position - 1);
        if (!(targetTask instanceof Event)) {
            throw new DukeException("Only events can have multiple slots, but the task you chose was not an event.");
        }
        ((Event) targetTask).addEventTime(s);
        return targetTask;
    }

    /**
     * This method specifies the slot of an event.
     *
     * @param position The position of the target event in the task list.
     * @param s The string of the new slot.
     * @return The event task with specified slot.
     * @throws DukeException If the position exceeds the size of the task list or the target task is not an event.
     */
    public Task specifyEventSlot(int position, String s) throws DukeException {
        if (position > getTotalNumber()) {
            throw new DukeException("There is no such task in the list, please specify a valid task.");
        }
        Task targetTask = taskList.get(position - 1);
        if (!(targetTask instanceof Event)) {
            throw new DukeException("Only events can have multiple slots, but the task you chose was not an event.");
        }
        ((Event) targetTask).specifyEventTime(s);
        return targetTask;
    }

    /**
     * This method record the task list into a task file.
     *
     * @throws IOException When the file cannot be written
     */
    public void rewrite() throws IOException {
        PrintWriter pw = new PrintWriter(".taskfile.txt");
        pw.close();
        BufferedWriter recorder = new BufferedWriter(
                new FileWriter(".taskfile.txt", true));
        for (Task t : taskList) {
            recorder.write(t.recordInfo());
            recorder.write("\n");
        }
        recorder.close();
    }
}
