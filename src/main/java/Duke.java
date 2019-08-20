import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greets user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        //Echos input
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = scan.nextLine();
        }

        //Exit
        System.out.println("Bye. Hope to see you again soon!");

    }
}
