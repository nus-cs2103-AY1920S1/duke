import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner myScanner = new Scanner(System.in);
        String argument = myScanner.nextLine();

        while (!argument.equals("bye") && !argument.equals("Bye")) {
            System.out.println(" " + argument);
            argument = myScanner.nextLine();

        }
        System.out.println(" Bye. Hope to see you again soon!");
        myScanner.close();
    }

    static void printLine() {
        System.out.println("_________)
    }
}
