import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        final String LINE = "____________________________________________________________";
        final String TAB = "    ";
        String cmd;
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        printDuke(LINE);
        printDuke("Hello! I'm Duke");
        printDuke("What can I do for you?");
        printDuke(LINE);

        Boolean isNotBye = true;
        while(isNotBye) {
            cmd = scan.nextLine();
            String arr[] = cmd.split(" ", 2);
            String firstWord = arr[0];

            switch (firstWord) {
                case "bye":
                    printDuke(LINE);
                    printDuke("Bye. Hope to see you again soon!");
                    printDuke(LINE);
                    isNotBye = false;
                    break;
                case "list":
                    printDuke(LINE);
                    System.out.println("    Here are the tasks in your list:");
                    if (list.isEmpty()) {
                        break;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("    " + (i + 1) + ". [" + list.get(i).getStatusIcon() + "]"  + list.get(i).getDesc());
                    }

                    printDuke(LINE);
                    break;
                case "done":
                    printDuke(LINE);
                    int taskNum = Integer.valueOf(arr[1]);
                    list.get(taskNum - 1).isDone(true);
                    printDuke("Nice! I've marked this task as done");
                    System.out.println("    " + taskNum + ". [" + list.get(taskNum - 1).getStatusIcon() + "]"
                                        + list.get(taskNum - 1).getDesc());
                    //printDuke();
                    printDuke(LINE);
                    break;
                default:
                Task t = new Task(cmd);
                list.add(t);
                printDuke(LINE);
                System.out.println(TAB + "added: " + cmd);
                printDuke(LINE);
            }
        }
    }

    public static void printDuke(String toPrint) {
        System.out.println("    " + toPrint);
    }
}