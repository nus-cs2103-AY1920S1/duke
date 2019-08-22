import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\n");
        System.out.println("How may I help you?\n");
        System.out.println("--------------------------------------\n");
        Task[] tasks = new Task[100];
        int numberOfTask = 0;
        boolean shouldStop = false;
        while (!shouldStop) {
            String input = sc.nextLine().trim();
            String command = input.split(" ")[0]; //the first word of the user input
            String description = input.substring(command.length()).trim();
            System.out.println("\n--------------------------------------");
            switch (command) {
                case "bye":
                    System.out.println("    Bye! See you again soon!!");
                    System.out.println("--------------------------------------");
                    shouldStop = true;
                    break;
                case "list":
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < numberOfTask; i++) {
                        System.out.println("    " + (i + 1) + "." + tasks[i]);
                    }
                    System.out.println("--------------------------------------");
                    break;
                case "done":
                    int taskNumber = Integer.parseInt(description) - 1;
                    tasks[taskNumber].complete();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + tasks[taskNumber].toString());
                    System.out.println("--------------------------------------");
                    break;
                case "todo":
                    System.out.println("    Got it. I've added this task: ");
                    tasks[numberOfTask] = new ToDoTask(description);
                    System.out.println("      " + tasks[numberOfTask].toString());
                    numberOfTask++;
                    System.out.println("    Now you have " + numberOfTask + " tasks in the list.");
                    System.out.println("--------------------------------------");
                    break;
                case "event":
                    System.out.println("    Got it. I've added this task: ");
                    tasks[numberOfTask] = new EventsTask(description);
                    System.out.println("      " + tasks[numberOfTask].toString());
                    numberOfTask++;
                    System.out.println("    Now you have " + numberOfTask + " tasks in the list.");
                    System.out.println("--------------------------------------");
                    break;
                case "deadline":
                    System.out.println("    Got it. I've added this task: ");
                    tasks[numberOfTask] = new DeadlinesTask(description);
                    System.out.println("      " + tasks[numberOfTask].toString());
                    numberOfTask++;
                    System.out.println("    Now you have " + numberOfTask + " tasks in the list.");
                    System.out.println("--------------------------------------");
                    break;
                default:
                    System.out.println("    unknown command");

                    System.out.println("--------------------------------------");
                    break;
            }
        }
    }
}
