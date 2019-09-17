package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.types.SortType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    protected Scanner sc;
    protected TaskList tasks;
    public String line = "    ____________________________________________________________";
    public String indent = "     ";
    public String hiMsg = "Hello! I'm Duke.\nWhat can I do for you?";
    public String byeMsg = "Bye. Hope to see you again soon!";
    public String addMsg = "Got it. I've added this task:";
    public String doneMsg = "Nice! I've marked this task as done:";
    public String notDoneMsg = "Nice! I've marked this task as not done:";
    public String delMsg = "Noted. I've removed this task:";
    public String emptyListMsg = "You have no task in the list";
    public List<String> outputs = new ArrayList<>();

    /**
     * Initialize a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Getter method for output buffer.
     * @return Output buffer as a list of output strings
     */
    public List<String> getOutputs() {
        return outputs;
    }

    /**
     * Set output buffer.
     * @param outputs Output buffer contain list of outputs
     */
    public void setOutputs(List<String> outputs) {
        this.outputs = outputs;
    }

    /**
     * Add string to program output buffer.
     *
     * @param output Output string
     */
    public void addToOutputs(String output) {
        outputs.add(output);
    }

    /**
     * Get program output string without clearing output buffer.
     *
     * @return Output string
     */
    public String getOutputString() {
        return String.join("\n", getOutputs());
    }

    /**
     * Get program output string and clear output buffer.
     *
     * @return Output String
     */
    public String getOutputStringAndClear() {
        String s = getOutputString();
        getOutputs().clear();
        return s;
    }

    /**
     * Read a line of input.
     *
     * @return Line of input.
     * @throws DukeException If no next line.
     */
    public String readCommand() throws DukeException {
        if (sc.hasNext()) {
            return sc.nextLine();
        } else {
            throw new DukeException("Scanner failed to get next line");
        }
    }

    public void showLine() {
        showMsg(line);
    }

    public void showMsg(String msg) {
        addToOutputs(msg);
    }

    public void showError(String message) {
        showMsg(message);
    }

    public void showHiMsg() {
        showMsg(hiMsg);
    }

    public void showByeMsg() {
        showMsg(byeMsg);
    }

    /**
     * Show add task message.
     *
     * @param t Task to be shown
     */
    public void showAddTaskMsg(Task t) {
        showMsg(addMsg);
        showTask(t);
        showListSize();
    }

    public void showTaskDoneMsg(Task t) {
        showMsg(doneMsg);
        showTask(t);
    }

    public void showTaskNotDoneMsg(Task t) {
        showMsg(notDoneMsg);
        showTask(t);
    }

    /**
     * Show message when task is deleted.
     *
     * @param t Task to be shown
     */
    public void showTaskDelMsg(Task t) {
        showMsg(delMsg);
        showTask(t);
        showListSize();
    }

    public void showEmptyListMsg() {
        showMsg(emptyListMsg);
    }

    /**
     * Show Welcome text.
     */
    public void showWelcome() {
        showHiMsg();
    }

    /**
     * Shows all tasks in task list.
     *
     * @throws DukeException If there is no task in task list
     */
    public void showTasks() throws DukeException {
        if (!tasks.isEmpty()) {
            showMsg("Here are the tasks in your list:");
            for (Task t: tasks.getTasks()) {
                showMsg((tasks.indexOf(t) + 1) + ". " + t);
            }
        } else {
            showEmptyListMsg();
        }
    }

    /**
     * Shows size of list.
     */
    public void showListSize() {
        if (!tasks.isEmpty()) {
            showMsg("Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list");
        } else {
            showEmptyListMsg();
        }
    }

    public void showTask(Task task) {
        showMsg("  " + task);
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public void showLoadTaskMsg() {
        showMsg("Loading data from file...");
    }

    public void showNoRecordsFoundMsg() {
        showMsg("No Records Found");
    }

    public void showSaveDataMsg() {
        showMsg("Saving data...");
    }

    public void showNothingToSaveMsg() {
        showMsg("Nothing to save.");
    }

    public void showSaveErrMsg() {
        showMsg("Error saving to file");
    }

    public void showDataSavedMsg() {
        showMsg("Data saved successfully");
    }

    public void showDataLoadedMsg(int size) {
        showMsg(size + " tasks loaded");
    }

    public void showLoadingError() {
        showMsg("Error loading tasks from file");
    }

    /**
     * Display matching tasks in TaskList.
     *
     * @param foundTasks List of found tasks.
     * @throws DukeException If there is no tasks in TaskList.
     */
    public void showFoundTasks(List<Task> foundTasks) throws DukeException {
        if (foundTasks.isEmpty()) {
            showNothingFoundMsg();
        }

        showMsg("Here are the matching tasks in your list:");
        foundTasks.forEach(t -> showMsg((foundTasks.indexOf(t) + 1) + ". " + t));
    }

    private void showNothingFoundMsg() {
        showMsg("No tasks found.");
    }

    public void showClearSuccessMsg() {
        showMsg("All tasks cleared.");
    }

    public void showSortSuccessMsg(SortType sortType) {
        showMsg("Tasks sorted according to " + sortType.toString());
    }
}
