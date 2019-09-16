public class GuiUI {

    public GuiUI() {

    }
    public String start() {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        String a = ("\nHello! I'm Jeanne");
        String b = ("\nWhat can I do for you?\n");
        String c = "\nYou may type 'help' for a list of commands!\n";
        return a + b + c;
    }

    public String bye() {
        return "\nBye. Hope to see you again soon!\n";
    }

    public String listOut() {
        return ("Here are the tasks in your list:");
    }

    public String listcount(int count) {
        return ("Now you have " + count + " tasks in the list.");
    }

    public String done() {
        return ("Nice! I've marked this task as done: ");
    }

    public String taskadded() {
        return ("Got it. I've added this task:");
    }

    public String find() {
        return ("\nHere are the matching tasks in your list:");
    }
}
