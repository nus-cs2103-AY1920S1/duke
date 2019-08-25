package duck.util;

import duck.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String indentation = "     ";
    private static final String separator = "    ____________________________________________________________\n";
    private static final String welcomeSentence = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String endingSentence = "Bye. Hope to see you again soon!";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    private String showFormattedStr(String str) {
        return separator + indentation + str.replace("\n", "\n" + indentation)
                + "\n" + separator;
    }

    public String showFormattedList(TaskList taskList) {
        String formattedList = separator;
        formattedList = formattedList + indentation + "Here are the tasks in your list:\n";
        if (taskList.getTotalTask() == 0) {
            return showFormattedStr("Currently there is no task~");
        }
        for (int i = 1; i <= taskList.getTotalTask(); i++) {
            formattedList = formattedList + indentation + i + ". " + taskList.getTaskAt(i - 1) + "\n";
        }
        formattedList = formattedList + separator;
        return formattedList;
    }

    public void showWelcome() {
        System.out.println(showFormattedStr(welcomeSentence));
    }

    public void showGoodbye() {
        System.out.println(showFormattedStr(endingSentence));
    }

    public void showSavingError() {
        System.out.println(showFormattedStr("☹ OOPS!!! We cannot save your data!"));
    }

    public void showLoadingError() {
        System.out.println(showFormattedStr("☹ OOPS!!! We cannot load your data!"));
    }

    public void showError(String errorMsg) {
        System.out.println(showFormattedStr(errorMsg));
    }

    public void showNoTask() {
        System.out.println(showFormattedStr("kkk ~ There is no task in your todo list now!"));
    }

    public void showTaskAdded(int total, Task newTask) {
        System.out.println(showFormattedStr("Got it. I've added this task:\n" + "  " + newTask
                + "\nNow you have " + total + " tasks in the list."));
    }


    public void showTaskDeleted(int total, Task removedTask) {
        System.out.println(showFormattedStr("Noted. I've removed this task: \n" + "  " + removedTask
                + "\nNow you have " + total + " tasks in the list."));
    }

    public void showTaskDone(Task doneTask) {
        System.out.println(showFormattedStr("Nice! I've marked this task as done:\n" + "  " + doneTask));
    }

    public void showFullList(TaskList taskList) {
        System.out.println(showFormattedList(taskList));
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
