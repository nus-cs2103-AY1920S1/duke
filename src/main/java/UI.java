public class Ui {

    public static void showGreeting() {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    //print common methods to call
    public static void printNoted() {
        System.out.println("Noted. I've removed this task:");
    }
    public static void printGotIt() {
        System.out.println("Got it. I've added this task:");
    }

    public static void printNow(int length) {
        System.out.println("Now you have " + length + " tasks in the list.");
    }

}
