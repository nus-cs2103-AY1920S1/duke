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
            String input = sc.nextLine();
            String[] command = input.split(" ");
            System.out.println("\n--------------------------------------");
            switch (command[0]) {
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
                    int taskNumber = Integer.parseInt(command[1]) - 1;
                    tasks[taskNumber].complete();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + tasks[taskNumber].toString());
                    System.out.println("--------------------------------------");
                    break;
                default:
                    System.out.println("    added: " + input);
                    tasks[numberOfTask] = new Task(input);
                    numberOfTask++;
                    System.out.println("--------------------------------------");
                    break;
            }
        }
    }
}
