import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String TABS = "     ";
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(TABS + "Hello! I'm Duke\n"+ TABS +"What can I do for you?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showDoneMessage() {
        System.out.println(TABS + "Nice! I've marked this task as done: ");
    }

    public void showDeleteMessage() {
        System.out.println(TABS + "Noted. I've removed this task: ");
    }

    public void showAddMessage() {
        System.out.println(TABS + "Got it. I've added this task: ");
    }

    public void showNumberOfTasks(Integer numberOfTasks){
        if (numberOfTasks == 1) {
            System.out.printf("%sNow you have %d task in the list.\n", TABS, numberOfTasks);
        } else {
            System.out.printf("%sNow you have %d tasks in the list.\n", TABS, numberOfTasks);
        }
    }

    public void showTask(Task taskToDisplay) {
        System.out.println(TABS + "  " + taskToDisplay.toString());
    }

    public void showExit() {
        System.out.println(TABS + "Bye. Hope to see you again soon!");
    }

    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println(TABS + "Here are the tasks in your list:");
        Integer index = 1;
        for (Task taskToShow : taskList) {
            System.out.println(TABS + index.toString() + "." + taskToShow.toString());
            index++;
        }
    }

    public void showSearchResults(HashMap<Integer, Task> taskList) {
        System.out.println(TABS + "Here are the matching tasks in your list:");

        for (Integer index : taskList.keySet()) {
            Task currentTask = taskList.get(index);
            System.out.println(TABS + index.toString() + "." + currentTask.toString());
        }
    }

    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(TABS + "☹ OOPS!!! " + message);
        System.out.println(LINE);
    }
    public String readCommand() {
        String userCommand = scanner.nextLine();
        return userCommand;
    }


}
