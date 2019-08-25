package duke.ui;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

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

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public Ui(TaskList tasks) {
        this.sc = new Scanner(System.in);
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public String readCommand() throws DukeException {
        if (sc.hasNext()) {
            return sc.nextLine();
        } else {
            throw new DukeException("Scanner failed to get next line");
        }
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showMsg(String msg) {
        System.out.println(indent + msg);
    }

    public void showError(String message) {
        showMsg(message);
    }

    public void showHiMsg() {
        showMsg(hiMsg);
    }

    public void showByeMsg() {
        showMsg(
                byeMsg);
    }

    public void showAddTaskMsg(Task t) {
        showMsg(addMsg);
        showTask(t);
        showListSize();
    }

    public void showTaskDoneMsg(Task t) {
        showMsg(doneMsg);
        showTask(t);
    }

    public void showTaskDelMsg(Task t) {
        showMsg(delMsg);
        showTask(t);
        showListSize();
    }

    public void showEmptyListMsg() {
        System.out.println(emptyListMsg);
    }

    public void showWelcome() {
        showLine();
        showHiMsg();
        showLine();
        System.out.println();
    }
    public void showTasks() {
        if (!tasks.isEmpty()) {
            showMsg("Here are the tasks in your list:");
            for (Task t: tasks.getTasks()) {
                showMsg((tasks.indexOf(t) + 1) + ". " + t);
            }
        } else {
            showEmptyListMsg();
        }
    }
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

    public void showLoadTaskMsg() {
        showMsg("Loading data from file...");
    }

    public void noRecordsFoundMsg() {
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
}
