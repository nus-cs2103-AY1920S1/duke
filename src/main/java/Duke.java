import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        printOutput(" " + logo + "\n\t Hello! I'm Duke" + "\n" + "\t What can I do for you?");

        while(sc.hasNext()) {
            String input = sc.next();
            if(input.equals("bye")) {
                printOutput("Bye. Hope to see you again soon!");
                return;
            } else {
                printOutput(input);
            }
        }
    }

    public static void printOutput(String output) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t " + output);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }
}

