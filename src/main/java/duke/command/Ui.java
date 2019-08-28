package duke.command;

public class Ui {

    private static String divider = "\t____________________________________________________________\n";
    private static String intro = "\t Hello! I'm Duke\n\t What can I do for you?\n";
    private static String goodbye = "\t Bye. Hope to see you again soon!\n";

    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        print(intro);
    }

    public void printGoodbye() {
        print(goodbye);
    }

    public void printToUser(String s) {
        print(s);
    }

    public void printErrToUser(Exception e) {
        System.err.print(divider);
        System.err.print(e);
        System.err.print(divider);
    }

    private static void print(String s) {
        System.out.print(divider);
        System.out.print(s);
        System.out.print(divider);
    }
}
