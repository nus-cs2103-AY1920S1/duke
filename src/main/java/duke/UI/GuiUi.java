package duke.UI;

public class GuiUi {
    /**
     * Utility class that sends general String to display to duke GUI
     * @return
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

    //print common methods to call
    public static String printNoted() {
        return "Noted. I've removed this task:";
    }
    public static String printGotIt() {
        return "Got it. I've added this task:";
    }
    public static String printNow(int length) {
        return "Now you have " + length + " tasks in the list.";
    }
    public static String helpText() {
        return  "List of commands are as follows:\n" +
                "[list]: list all tasks\n" +
                "[done <number of task in list>]: mark particular task as done \n" +
                "[todo <task name>]: create a todo Task \n" +
                "[deadline <task name> /by dd/mm/yy hhmm]: create task with a specified deadline \n" +
                "[event <task name> /at dd/mm/yy hhmm-hhmm]: create task with specified event \n" +
                "[bye]: closes applications";
    }
}
