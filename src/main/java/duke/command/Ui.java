package duke.command;

import duke.task.Task;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    Parser parser;
    String s;

    public Ui() {
    }

    /**
     * Handles input from user and pass it to parser for processing.
     *
     * @param parser parser to process the input.
     */
    public String start(Parser parser, String input) {
        s = "";
        this.parser = parser;
        if (!input.equals("bye")) {
            try {
                parser.process(input);
            } catch (DukeException e) {
                s = e.getMessage();
            } catch (NumberFormatException e) {
                s = "☹ OOPS!!! The task done must be a number.";
            } catch (ParseException e) {
                s = "☹ OOPS!!! Wrong date format.\nPlease use the correct format dd/MM/yyyy hh:mm";
            }
        }
        return s;
    }

    /**
     * Prints message when adding task.
     *
     * @param currTask current task being added.
     * @param taskList list of tasks to be added.
     */
    public void printAdd(Task currTask, TaskList taskList) {
        ArrayList<Task> currList = taskList.list;
        int size = currList.size();
        s = "Got it. I've added this task: \n  " + currTask
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Prints message when deleting task.
     *
     * @param currTask current task being deleted.
     * @param taskList list of tasks to be deleted from.
     */
    public void printDelete(Task currTask, TaskList taskList) {
        s = "Noted. I've removed this task: \n  " + currTask
                + "\nNow you have " + taskList.list.size() + " tasks in the list.";
    }

    /**
     * Prints current list.
     *
     * @param list list of task to be printed.
     */
    public void printList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            s += "There are no tasks in your list.";
        } else {
            for (int i = 1; i <= list.size(); i++) {
                s += i + ". " + list.get(i - 1) + "\n";
            }
        }
    }

    /**
     * Prints list of reminder.
     *
     * @param list list of reminders to be printed.
     */
    public void printReminder(ArrayList<Task> list) {
        if (list.isEmpty()) {
            s += "There are no tasks in your list.";
        } else {
            s += "Reminders:\n";
            for (int i = 1; i <= list.size(); i++) {
                Task curr = list.get(i - 1);
                if (curr.getDiffDays() <= 3 && curr.getDiffDays() >= 0) {
                    s += curr + "\n";
                }
            }
        }
    }

    /**
     * Prints message when a task is done.
     *
     * @param currTask current task which is done.
     */
    public void printDone(Task currTask) {
        s = "Nice! I've marked this task as done: \n  " + currTask;
    }

    /**
     * Prints list of tasks which contains keyword.
     *
     * @param list list of task to be printed.
     */
    public void printFind(ArrayList<Task> list) {
        s = "Here are the matching tasks in your list:";
        for (int i = 1; i <= list.size(); i++) {
            s += (i + ". " + list.get(i - 1));
        }
    }

    /**
     * Prints loading error.
     */
    public void showLoadingError() {
        s = "File not found.";
    }
}
