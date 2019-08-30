package logic;

import task.Task;

import java.util.List;

/**
 * Deals with interactions with the user and output on CLI
 */
public class Ui {
    /**
     * Wraps strings for style and formatting
     *
     * @param input String parameter to be wrapped and printed
     */
    public static void printStr(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(input + "\n");
        sb.append("____________________________________________________________\n");
        System.out.println(sb.toString());
    }

    /**
     * Prints out greeting string at start of app
     */
    public void greet() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?");
        printStr(sb.toString());
    }

    /**
     * Prints out ordered list of tasks
     *
     * @param taskList List of Tasks
     */
    public void printList(List<Task> taskList, boolean isMatching) {
        StringBuilder sb = new StringBuilder();
        if (isMatching) {
            sb.append("Here are the matching tasks in your list:\n");
        } else {
            sb.append("Here are the tasks in your list:\n");
        }
      
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1) + ". " + taskList.get(i).toString());
            if (i != taskList.size() - 1) { //last item
                sb.append("\n");
            }
        }
      
        printStr(sb.toString());
    }

    /**
     * Exit Message to be printed
     */
    public void bye() {
        printStr("Bye. Hope to see you again soon!");
    }

}
