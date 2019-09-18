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
    public static final String WELCOME_Msg = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String ENDING_Msg = "Bye. Hope to see you again soon!";
    public static final String SAVING_ERROR_Msg = "☹ OOPS!!! We cannot save your data!";
    public static final String LOADING_ERROR_Msg = "☹ OOPS!!! We cannot load your data!";
    public static final String NO_TASK_Msg = "kkk ~ There is no task in your todo list now!";
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
        System.out.println(getFormattedStr(WELCOME_Msg));
        return WELCOME_Msg;
    }

    public String showGoodbye() {
        System.out.println(getFormattedStr(ENDING_Msg));
        return ENDING_Msg;
    }

    public String showSavingError() {
        System.out.println(getFormattedStr(SAVING_ERROR_Msg));
        return SAVING_ERROR_Msg;
    }

    public String showLoadingError() {
        System.out.println(getFormattedStr(LOADING_ERROR_Msg));
        return LOADING_ERROR_Msg;
    }

    public String showError(String errorMsg) {
        System.out.println(getFormattedStr(errorMsg));
        return errorMsg;
    }

    public String showNoTask() {
        System.out.println(getFormattedStr(NO_TASK_Msg));
        return NO_TASK_Msg;
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

    public String showTaskDuplicated() {
        String msg = "A same task already exists!!! Do not bother yourself to do it twice=)";
        System.out.println(getFormattedStr(msg));
        return msg;
    }

    public String showFullList(TaskList taskList) {
        if (taskList.getTotalTask() == 0) {
            return showNoTask();
        } else {
            String formattedList = "";
            String header = "Here are the tasks in your list:\n";
            for (int i = 1; i <= taskList.getTotalTask(); i++) {
                formattedList = formattedList + INDENTATION + i + ". " + taskList.getTaskAt(i - 1) + "\n";
            }
            System.out.println(SEPARATOR + INDENTATION + header + formattedList + SEPARATOR);
            return header + formattedList;
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
