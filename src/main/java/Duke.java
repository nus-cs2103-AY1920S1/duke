import java.util.Scanner;

public class Duke {
    private static void straightLine() {
        System.out.println("\n--------------------------------------");
    }
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
        straightLine();
        Task[] tasks = new Task[100];
        int numberOfTask = 0;
        boolean shouldStop = false;
        while (!shouldStop) {
            try {
                String input = sc.nextLine().trim();
                String command = input.split(" ")[0]; //the first word of the user input
                String description = input.substring(command.length()).trim();
                straightLine();
                switch (command) {
                    case "bye":
                        System.out.println("    Bye! See you again soon!!");
                        straightLine();
                        shouldStop = true;
                        break;
                    case "list":
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 0; i < numberOfTask; i++) {
                            System.out.println("    " + (i + 1) + "." + tasks[i]);
                        }
                        straightLine();
                        break;
                    case "done":
                        int taskNumber;
                        try {
                            taskNumber = Integer.parseInt(description) - 1;
                            if (taskNumber > numberOfTask) {
                                throw new InvalidDescriptionDukeException(command, description);
                            }
                        } catch (NumberFormatException err) {
                            throw new InvalidDescriptionDukeException(command, description);
                        }
                        tasks[taskNumber].complete();
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("      " + tasks[taskNumber].toString());
                        straightLine();
                        break;
                    case "todo":
                        tasks[numberOfTask] = new ToDoTask(description);
                        System.out.println("    Got it. I've added this task: ");
                        System.out.println("      " + tasks[numberOfTask].toString());
                        numberOfTask++;
                        System.out.println("    Now you have " + numberOfTask + " tasks in the list.");
                        straightLine();
                        break;
                    case "event":
                        tasks[numberOfTask] = new EventsTask(description);
                        System.out.println("    Got it. I've added this task: ");
                        System.out.println("      " + tasks[numberOfTask].toString());
                        numberOfTask++;
                        System.out.println("    Now you have " + numberOfTask + " tasks in the list.");
                        straightLine();
                        break;
                    case "deadline":
                        tasks[numberOfTask] = new DeadlinesTask(description);
                        System.out.println("    Got it. I've added this task: ");
                        System.out.println("      " + tasks[numberOfTask].toString());
                        numberOfTask++;
                        System.out.println("    Now you have " + numberOfTask + " tasks in the list.");
                        straightLine();
                        break;
                    default:
                        throw new InvalidCommandDukeException(command);

                }
             } catch(DukeException err) {
                System.out.println("    " + err.toString());
                straightLine();
            }
        }
    }
}
