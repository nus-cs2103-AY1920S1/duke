import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean readingCommands = true;
        while (readingCommands) {
            String input = sc.nextLine();
            String[] inputWords = input.split(" ");
            String command = inputWords[0];

            switch (command) {
                case "list": {
                    for (int i = 1; i <= tasks.size(); i++) {
                        Task task = tasks.get(i - 1);
                        System.out.println(i + ". " + task);
                    }
                    break;
                }

                case "done": {
                    int taskId = Integer.parseInt(inputWords[1]) - 1;
                    Task task = tasks.get(taskId);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    break;
                }

                case "bye": {
                    readingCommands = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                default: {
                    Task task = new Task(input);
                    tasks.add(task);
                    System.out.println("added: " + task.description);
                }
            }
        }
        sc.close();
    }
}

