import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner scn = new Scanner(System.in);

        String command = scn.nextLine();
        String[] commands = new String[100];
        int commands_count = 0;

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < commands_count; i++) {
                    System.out.println((i + 1) + ". " + commands[i]);
                }
            } else {
                commands[commands_count] = command;
                System.out.println("added: " + command);
                commands_count++;
            }
            command = scn.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
