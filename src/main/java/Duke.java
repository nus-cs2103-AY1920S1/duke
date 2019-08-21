import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> task = new ArrayList<>();

    public static void printLine() {
        System.out.println("   ____________________________________________________________");
    }

    public static void printGreet() {
        printLine();
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();
        System.out.println("");
    }

    public static void readInput() {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (line.equals("bye")) {
                printLine();
                System.out.println("    Bye. Hope to see you again soon!");
                printLine();
                System.out.println("");
                break;
            }

            switch (line) {
                case "list":
                    int index = 1;
                    printLine();
                    System.out.println("    " + "Here are the tasks in your list:");
                    for (int i = 0; i < task.size(); i++) {
                        System.out.println("    " + index++ + "." + task.get(i));
                    }
                    printLine();
                    System.out.println("");
                    break;

                default:
                    String[] data = line.split(" ");
                    if (data[0].equals("done")) {
                        printLine();
                        System.out.println("    " + "Nice! I've marked this task as done:");
                        int item = Integer.parseInt(data[1]);
                        Task t = task.get(--item);
                        t.markAsDone();
                        System.out.println("      " + t);
                        printLine();
                        System.out.println("");
                    } else {
                        task.add(new Task(line));
                        printLine();
                        System.out.println("    " + "added: " + line);
                        printLine();
                        System.out.println("");
                    }
            }
        }
    }

    public static void main(String[] args) {
        printGreet();
        readInput();
    }
}