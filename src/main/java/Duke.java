import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Task storinglist[];
        int i = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcome = "--------------------------\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "\n"
                + "--------------------------";

        System.out.println(welcome);

        Scanner scanner = new Scanner(System.in);

        storinglist = new Task[100];


        while (true) {
            String command = scanner.nextLine();

            if (command.equals("list")) {
                System.out.println("--------------------------");
                for (int x = 0; x < i; x++) {
                    System.out.println(x + 1 + ". " + storinglist[x]);
                }

                System.out.println("\n--------------------------");
            } else if (command.contains("done")) {
                String splitstring[] = command.split(" ");
                int taskdone = Integer.valueOf(splitstring[1]);
                storinglist[taskdone - 1].markasdone();
                System.out.println("--------------------------\n"
                        + "Nice! I've marked this tasked as done:\n"
                        + storinglist[taskdone - 1]
                        + "\n\n--------------------------");
            } else if (command.equals("bye")) {
                System.out.println("--------------------------\n"
                        + "Bye. Hope to see you again soon!\n\n"
                        + "--------------------------");
                break;
            } else {
                storinglist[i] = new Task(command);
                i++;
                System.out.println("--------------------------\n"
                        + "added: " + command
                        + "\n\n--------------------------");
            }
        }
    }
}
