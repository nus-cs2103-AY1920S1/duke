package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * This class handles the interactions with users. Two main features are (1) reading users' inputs from the console; (2)
 * showing users the results responding to their commands. Many strings are pre-defined in certain formats. Pre-defined
 * string messages are <code>showWelcome</code>,
 * <code>showGoodbye</code>, <code>showSavingError</code>, <code>showLoadingError</code>, <code>showNoTask</code>. The
 * rest needs to take in some parameter(s) to show according responses.
 */
public class Ui {
    private static final String indentation = "     ";
    private static final String separator = "    ____________________________________________________________\n";
    private static final String welcomeSentence = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String endingSentence = "Bye. Hope to see you again soon!";
    private Scanner scanner;

    /**
     * This is a sole constructor initialising its <code>Scanner</code> to read from console.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    private String getFormattedStr(String str) {
        return separator + indentation + str.replace("\n", "\n" + indentation)
                + "\n" + separator;
    }

    public void showWelcome() {
        System.out.println(getFormattedStr(welcomeSentence));
    }

    public void showGoodbye() {
        System.out.println(getFormattedStr(endingSentence));
    }

    public void showSavingError() {
        System.out.println(getFormattedStr("☹ OOPS!!! We cannot save your data!"));
    }

    public void showLoadingError() {
        System.out.println(getFormattedStr("☹ OOPS!!! We cannot load your data!"));
    }

    public void showError(String errorMsg) {
        System.out.println(getFormattedStr(errorMsg));
    }

    public void showNoTask() {
        System.out.println(getFormattedStr("kkk ~ There is no task in your todo list now!"));
    }

    public void showTaskAdded(int total, Task newTask) {
        System.out.println(getFormattedStr("Got it. I've added this task:\n" + "  " + newTask
                + "\nNow you have " + total + " tasks in the list."));
    }


    public void showTaskDeleted(int total, Task removedTask) {
        System.out.println(getFormattedStr("Noted. I've removed this task: \n" + "  " + removedTask
                + "\nNow you have " + total + " tasks in the list."));
    }

    public void showTaskDone(Task doneTask) {
        System.out.println(getFormattedStr("Nice! I've marked this task as done:\n" + "  " + doneTask));
    }

    public void showFullList(TaskList taskList) {
        if (taskList.getTotalTask() == 0) {
            showNoTask();
        } else {
            String formattedList = separator;
            formattedList = formattedList + indentation + "Here are the tasks in your list:\n";
            for (int i = 1; i <= taskList.getTotalTask(); i++) {
                formattedList = formattedList + indentation + i + ". " + taskList.getTaskAt(i - 1) + "\n";
            }
            formattedList = formattedList + separator;
            System.out.println(formattedList);
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
