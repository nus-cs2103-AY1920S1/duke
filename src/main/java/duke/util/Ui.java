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
    private static final String INDENTATION = "     ";
    private static final String SEPARATOR = "    ____________________________________________________________\n";
    private static final String WELCOME_MSG = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String ENDING_MSG = "Bye. Hope to see you again soon!";
    private static final String SAVING_ERROR_MSG = "☹ OOPS!!! We cannot save your data!";
    private static final String LOADING_ERROR_MSG = "☹ OOPS!!! We cannot load your data!";
    private static final String NO_TASK_MSG = "kkk ~ There is no task in your todo list now!";
    private static final String DUPLICATES_NOTIFY_MSG = "A same task exists. Do you still want to add?" + "(y for yes)";
    private static final String IGNORE_MSG = "Okay! I ignored this duplicate task!";
    private Scanner scanner;

    /**
     * This is a sole constructor initialising its <code>Scanner</code> to read from console.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    private String getFormattedStr(String str) {
        return SEPARATOR + INDENTATION + str.replace("\n", "\n" + INDENTATION)
                + "\n" + SEPARATOR;
    }

    public String showWelcome() {
        System.out.println(getFormattedStr(WELCOME_MSG));
        return WELCOME_MSG;
    }

    public String showGoodbye() {
        System.out.println(getFormattedStr(ENDING_MSG));
        return ENDING_MSG;
    }

    public String showSavingError() {
        System.out.println(getFormattedStr(SAVING_ERROR_MSG));
        return SAVING_ERROR_MSG;
    }

    public String showLoadingError() {
        System.out.println(getFormattedStr(LOADING_ERROR_MSG));
        return LOADING_ERROR_MSG;
    }

    public String showError(String errorMsg) {
        System.out.println(getFormattedStr(errorMsg));
        return errorMsg;
    }

    public String showNoTask() {
        System.out.println(getFormattedStr(NO_TASK_MSG));
        return NO_TASK_MSG;
    }

    public String showIgnore() {
        System.out.println(getFormattedStr(IGNORE_MSG));
        return IGNORE_MSG;
    }

    public String showTaskAdded(int total, Task newTask) {
        String msg = "Got it. I've added this task:\n" + "  " + newTask
                + "\nNow you have " + total + " tasks in the list.";
        System.out.println(getFormattedStr(msg));
        return msg;
    }


    public String showTaskDeleted(int total, Task removedTask) {
        String msg = "Noted. I've removed this task: \n" + "  " + removedTask
                + "\nNow you have " + total + " tasks in the list.";
        System.out.println(getFormattedStr(msg));
        return msg;
    }

    public String showTaskDone(Task doneTask) {
        String msg = "Nice! I've marked this task as done:\n" + "  " + doneTask;
        System.out.println(getFormattedStr(msg));
        return msg;
    }

    public String showTaskDuplicating() {
        System.out.println(getFormattedStr(DUPLICATES_NOTIFY_MSG));
        return DUPLICATES_NOTIFY_MSG;
    }

    public String showFullList(TaskList taskList) {
        if (taskList.getTotalTask() == 0) {
            return showNoTask();
        } else {
            String formattedList = SEPARATOR;
            formattedList = formattedList + INDENTATION + "Here are the tasks in your list:\n";
            for (int i = 1; i <= taskList.getTotalTask(); i++) {
                formattedList = formattedList + INDENTATION + i + ". " + taskList.getTaskAt(i - 1) + "\n";
            }
            formattedList = formattedList + SEPARATOR;
            System.out.println(formattedList);
            return formattedList;
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
