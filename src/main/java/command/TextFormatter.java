package command;

import task.Task;
import java.util.ArrayList;


    /**
    * Formats all objects to the appropriate String form
    */

public class TextFormatter {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

        /**
         * Returns the arrayList of tasks as a String
         *
         * @param input an arraylist of all the tasks
         * @return String of tasks
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

        return  listMessageTitle + tasks;
    }

        /**
         * Returns the deleted task and the total tasks left as a String
         *
         * @param removed Task that has been removed
         * @param size number of remaining tasks
         * @return String of the deleted task
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
         * Returns the done task
         *
         * @param done Task that has been done
         * @return String of done task
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
         * Returns the error
         *
         * @param error Exception that has been thrown
         * @return String of error
         */


    public static String errorFormat(Exception error) {
        String separator = "    ____________________________________________________________ + \n";
        String err = "    " + error.getMessage();
        return separator + err + "\n" + separator;
    }

        /**
         * Returns the added task and the total tasks left as a String
         *
         * @param inputTask Task that has been added
         * @param size number of remaining tasks
         * @return String of the added task
         */

    public static String addFormat(Task inputTask, int size) {
        String separator = "    ____________________________________________________________\n";
        String addingTask = "    Got it. I've added this task:\n";
        String converted = "        "+ inputTask.toString() + "\n";
        String taskTracking = "    Now you have " + size + " tasks in the list.\n";
        return  addingTask + converted + taskTracking;
    }

        /**
         * Returns the exit message as a String
         *
         * @return String saying bye
         */

    public static String byeFormat() {
        String end = "Bye. Hope to see you again soon!";
        String separator = "    ____________________________________________________________\n";
        String converted = "    " + end + "\n";
        return separator + converted + separator;
    }

        /**
         * Returns the hello message as a String
         *
         * @return String saying hello
         */

    public static String helloFormat() {
        String start = "Hello! I'm Duke\n    What can I do for you?";
        String separator = "    ____________________________________________________________\n";
        String converted = "    " + start + "\n";
        return separator + converted + separator;
    }

        /**
         * Returns the arrayList of matched tasks as a String of the list
         *
         * @param input an arraylist of all the tasks that match the search String
         * @return String of searched tasks
         */


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

        /**
         * Returns an array of the statistics as a String
         *
         * @param stats an array of the collated number of different types of tasks
         * @return String of statistics
         */

    public static String statsFormat(int[] stats) {
        String separator = "    ____________________________________________________________\n";
        String statsMessageTitle = "    Here are your statistics: \n";
        StringBuilder myTasks = new StringBuilder();
        myTasks.append("Total Tasks : " + stats[0] + "\n");
        myTasks.append("Total Tasks Done : " + stats[1] + "\n");
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


