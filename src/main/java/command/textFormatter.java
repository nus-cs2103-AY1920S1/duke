package command;
import java.util.ArrayList;
import task.Task;

/**
 *
 */

public class textFormatter {
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






}
