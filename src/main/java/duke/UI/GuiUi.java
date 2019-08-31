package duke.UI;

public class GuiUi {

    public static String showGreeting() {
        String temp = "";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        temp = "Hello from\n" + logo;
        temp += "\n";
        temp += "Hello! I'm Duke";
        temp += "\n";
        temp += "What can I do for you?";
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

}
