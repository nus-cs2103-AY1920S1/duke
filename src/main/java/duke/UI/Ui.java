package duke.ui;

public class Ui {
    /**
     * Utility class that sends general String to display to duke GUI.
     * @return String of greeting
     */
    public static String showGreeting() {
        String temp = "";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        temp = "Hello from\n" + logo + "\n";
        temp += "Hello! I'm Duke\n";
        temp += "What can I do for you?\n";
        temp += "Type help for a list of commands";
        return temp;
    }

    /**
     * returns noted response.
     * @return noted response
     */
    public static String printNoted() {

        return "Noted. I've removed this task:";
    }

    /**
     * returns got it response.
     * @return got it response
     */
    public static String printGotIt() {

        return "Got it. I've added this task:";
    }

    /**
     * return back length of list response.
     * @param length length of list
     * @return length response
     */
    public static String printNow(int length) {

        return "Now you have " + length + " tasks in the list.";
    }

    /**
     * returns help text.
     * @return help text
     */
    public static String helpText() {
        return  "List of commands are as follows:\n\n"
                +
                "[list]: list all tasks\n"
                +
                "[done <number of task in list>]: mark particular task as done \n"
                +
                "[todo <task name>]: create a todo Task \n"
                +
                "[deadline <task name> /by dd/mm/yy hhmm]: \n create task with a specified deadline \n"
                +
                "[event <task name> /at dd/mm/yy hhmm-hhmm]: \n create task with specified event \n"
                +
                "[find <query>] searches ur list of tasks and return a result \n\n"
                +
                "[edit]: edit has three modes:\n"
                +
                "first, edit both date and time:\n"
                +
                "    [edit <item num> <desc> t: <dd/mm/yy hhmm-hhmm>] \n"
                +
                "    ****second 'hhmm' can be omitted for Deadline task\n"
                +
                "    ****for todo Task, end input with t:\n"
                +
                "  edit desc only:\n"
                +
                "    [edit <item num> desc <desc>]\n"
                +
                "   edit time only:\n"
                +
                "    [edit <item num> time dd/m/yy hhmm]\n\n"
                +
                "[bye]: closes applications";
    }
}
