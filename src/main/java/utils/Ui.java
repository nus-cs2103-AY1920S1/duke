package utils;
/**
 * Ui class handles everything to do with printing to console and formatting strings.
 */

public class Ui {

    public Ui() {
        logo();
        greeting();
    }

    void logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * inital message from duke.
     */

    static void greeting() {
        String output = "    ____________________________________________________________\n" 
            + "     Hello! I'm Duke\n"
            + "     What can I do for you?\n"
            + "    ____________________________________________________________\n";
        System.out.println(output);
    }
    
    static String addDoubleLine(String str) {
        String line = "    ____________________________________________________________";
        return line + "\n" + str + "\n" + line;
    }

    public void printMsg(Task t, int size) {
        System.out.println(addDoubleLine("     Got it. I've added this task: \n" 
            + "      " + t.toString() + "\n     Now you have " + size + " tasks in the list."));
    }

    public void print(String str) {
        System.out.println(addDoubleLine(str));
    }

    public void printTab(String str) {
        print("    " + str);
    }
    
    public void bye() {
        System.out.println(addDoubleLine("     Bye. Hope to see you again soon!"));
    }
}