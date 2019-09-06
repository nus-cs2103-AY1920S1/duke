package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    protected Scanner sc;
    protected TaskList tasks;
    public String line = "    ____________________________________________________________";
    public String indent = "     ";
    public String hiMsg = "Hello! I'm Duke" + "\n" + indent + "What can I do for you?";
    public String byeMsg = "Bye. Hope to see you again soon!";
    public String addMsg = "Got it. I've added this task:";
    public String doneMsg = "Nice! I've marked this task as done:";
    public String delMsg = "Noted. I've removed this task:";
    public String emptyListMsg = "You have no task in the list";

    private List<String> list = new ArrayList<>();

    /**
     * Initialize a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Read a line of input.
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

    public String showLine() {
        return line;
    }

    public String showMsg(String msg) {
        return msg;
    }

    public String showError(String message) {
        return showMsg(message);
    }

    public String showHiMsg() {
        return showMsg(hiMsg);
    }

    public String showByeMsg() {
        return showMsg(byeMsg);
    }

    /**
     * Show add task message.
     *
     * @param t Task to be shown
     */
    public String showAddTaskMsg(Task t) {
        list.clear();
        list.add(showMsg(addMsg));
        list.add(showTask(t));
        list.add(showListSize());
        return String.join("\n", list);
    }

    public String showTaskDoneMsg(Task t) {
        list.clear();
        list.add(showMsg(doneMsg));
        list.add(showTask(t));
        return String.join("\n", list);
    }

    /**
     * Show message when task is deleted.
     *
     * @param t Task to be shown
     */
    public String showTaskDelMsg(Task t) {
        list.clear();
        list.add(showMsg(delMsg));
        list.add(showTask(t));
        list.add(showListSize());
        return String.join("\n", list);
    }

    public String showEmptyListMsg() {
        return showMsg(emptyListMsg);
    }

    /**
     * Show Welcome text.
     */
    public String showWelcome() {
        list.clear();
        list.add(showLine());
        list.add(showHiMsg());
        list.add(showLine());
        return String.join("\n", list);
    }

    /**
     * Shows all tasks in task list.
     *
     * @throws DukeException If there is no task in task list
     */
    public String showTasks() throws DukeException {
        list.clear();
        if (!tasks.isEmpty()) {
            list.add(showMsg("Here are the tasks in your list:"));
            for (Task t: tasks.getTasks()) {
                list.add(showMsg((tasks.indexOf(t) + 1) + ". " + t));
            }
        } else {
            list.add(showEmptyListMsg());
        }
        return String.join("\n", list);
    }

    /**
     * Shows size of list.
     */
    public String showListSize() {
        if (!tasks.isEmpty()) {
            return showMsg("Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list");
        } else {
            return showEmptyListMsg();
        }
    }

    public String showTask(Task task) {
        return showMsg("  " + task);
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public String showLoadTaskMsg() {
        return showMsg("Loading data from file...");
    }

    public String noRecordsFoundMsg() {
        return showMsg("No Records Found");
    }

    public String showSaveDataMsg() {
        return showMsg("Saving data...");
    }

    public String showNothingToSaveMsg() {
        return showMsg("Nothing to save.");
    }

    public String showSaveErrMsg() {
        return showMsg("Error saving to file");
    }

    public String showDataSavedMsg() {
        return showMsg("Data saved successfully");
    }

    public String showDataLoadedMsg(int size) {
        return showMsg(size + " tasks loaded");
    }

    public String showLoadingError() {
        return showMsg("Error loading tasks from file");
    }

    public String showFoundTasks(List<Task> foundTasks) throws DukeException {
        list.clear();
        if (!foundTasks.isEmpty()) {
            list.add(showMsg("Here are the matching tasks in your list:"));
            for (Task t: foundTasks) {
                list.add(showMsg((foundTasks.indexOf(t) + 1) + ". " + t));
            }
        } else {
            list.add(showNothingFoundMsg());
        }
        return String.join("\n", list);
    }

    private String showNothingFoundMsg() {
        return showMsg("No tasks found.");
    }

}
