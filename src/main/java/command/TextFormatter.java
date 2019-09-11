package command;

import task.Task;
import java.util.ArrayList;


/**
 *
 */

public class TextFormatter {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     *
     */


    public static String listFormat(ArrayList<Task> input) {
        String separator = "    ____________________________________________________________\n";
        String listMessageTitle = "    Here are the tasks in your list:\n";
        StringBuilder myTasks = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            String temp = "    " + (i + 1) + "." + input.get(i) + "\n";
            myTasks.append(temp);
        }
        String tasks = myTasks.toString();

        return separator + listMessageTitle + tasks + separator;
    }

    /**
     *
     */

    public static String deleteFormat(Task removed, int size) {
        String separator = "    ____________________________________________________________";
        String removingTask = "    Noted. I've removed this task:";
        String converted = "      "+ removed;
        String taskTracking = "    Now you have " + size + " tasks in the list.";
        String answer = separator + "\n" + removingTask + "\n"
            + converted + "\n" + taskTracking + "\n" + separator + "\n";

       return answer;
}

    /**
     *
     */

    public static String doneFormat(Task done) {
        String firstLine = "Nice! I've marked this task as done:\n";
        String secondLine = "      " + done;
        String answer =  firstLine + secondLine;
        String separator = "    ____________________________________________________________\n";
        String converted = "    " + answer + "\n";
        return separator + converted + separator;
    }

    /**
     *
     */


    public static String errorFormat(Exception error) {
        String separator = "    ____________________________________________________________ + \n";
        String err = "    " + error.getMessage();
        return separator + err + "\n" + separator;
    }

    /**
     *
     */

    public static String addFormat(Task inputTask, int size) {
        String separator = "    ____________________________________________________________\n";
        String addingTask = "    Got it. I've added this task:\n";
        String converted = "        "+ inputTask.toString() + "\n";
        String taskTracking = "    Now you have " + size + " tasks in the list.\n";
        return separator + addingTask + converted + taskTracking + separator;
    }

    /**
     *
     */

    public static String byeFormat() {
        String end = "Bye. Hope to see you again soon!";
        String separator = "    ____________________________________________________________\n";
        String converted = "    " + end + "\n";
        return separator + converted + separator;
    }

    /**
     *
     */

    public static String helloFormat() {
        String start = "Hello! I'm Duke\n    What can I do for you?";
        String separator = "    ____________________________________________________________\n";
        String converted = "    " + start + "\n";
        return separator + converted + separator;
    }


    public static String searchFormat(ArrayList<Task> input) {
        String separator = "    ____________________________________________________________\n";
        String listMessageTitle = "    Here are the matching tasks in your list:\n";
        StringBuilder myTasks = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            String temp = "    " + (i + 1) + "." + input.get(i) + "\n";
            myTasks.append(temp);
        }
        String tasks = myTasks.toString();

        return separator + listMessageTitle + tasks + separator;
    }

    public static String statsFormat(int[] stats) {
        String separator = "    ____________________________________________________________\n";
        String statsMessageTitle = "    Here are your statistics: \n";
        StringBuilder myTasks = new StringBuilder();
        myTasks.append("Total Tasks :" + stats[0] + "\n");
        myTasks.append("Total Tasks Done:" + stats[1] + "\n");
        myTasks.append("Total ToDo Tasks:" + stats[2] + "\n");
        myTasks.append("Total ToDo Tasks Done:" + stats[3] + "\n");
        myTasks.append("Total Deadline Tasks:" + stats[4] + "\n");
        myTasks.append("Total Deadline Tasks Done:" + stats[5] + "\n");
        myTasks.append("Total Event Tasks:" + stats[6] + "\n");
        myTasks.append("Total Event Tasks Done:" + stats[7] + "\n");

        String statsString = myTasks.toString();
        return separator + statsMessageTitle + statsString + separator;
    }

    }


