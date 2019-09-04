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
    private static final String savingError = "☹ OOPS!!! We cannot save your data!";
    private static final String loadingError = "☹ OOPS!!! We cannot load your data!";
    private static final String noTaskMsg = "kkk ~ There is no task in your todo list now!";
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

    public String showWelcome() {
        System.out.println(getFormattedStr(welcomeSentence));
        return welcomeSentence;
    }

    public String showGoodbye() {
        System.out.println(getFormattedStr(endingSentence));
        return endingSentence;
    }

    public String showSavingError() {
        System.out.println(getFormattedStr(savingError));
        return savingError;
    }

    public String showLoadingError() {
        System.out.println(getFormattedStr(loadingError));
        return loadingError;
    }

    public String showError(String errorMsg) {
        System.out.println(getFormattedStr(errorMsg));
        return errorMsg;
    }

    public String showNoTask() {
        System.out.println(getFormattedStr(noTaskMsg));
        return noTaskMsg;
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

    public String showFullList(TaskList taskList) {
        if (taskList.getTotalTask() == 0) {
            return showNoTask();
        } else {
            String formattedList = separator;
            formattedList = formattedList + indentation + "Here are the tasks in your list:\n";
            for (int i = 1; i <= taskList.getTotalTask(); i++) {
                formattedList = formattedList + indentation + i + ". " + taskList.getTaskAt(i - 1) + "\n";
            }
            formattedList = formattedList + separator;
            System.out.println(formattedList);
            return formattedList;
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
