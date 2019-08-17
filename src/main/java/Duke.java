import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        Task[] tasks = new Task[100];
        int index = 0;
        while (!command.equals("bye")) {
            if (!command.equals("list") && !command.substring(0,4).equals("done")) { //for adding new task
                tasks[index] = new Task(command);
                index++;
                String commandMsg = "added: " + command + "\n";
                System.out.println(commandMsg);
            } else if (command.substring(0,4).equals("done")) { //for marking task as done
                int curr = Integer.parseInt(command.substring(5));
                tasks[curr - 1].markAsDone();
                String markAsDoneMsg = "Nice! I've marked this task as done:\n" +
                        "[" + tasks[curr - 1].getStatusIcon() + "] " + tasks[curr - 1].getDescription() + "\n";
                System.out.println(markAsDoneMsg);
            } else { //for listing tasks out
                String listMsg = "Here are the tasks in your list:";
                System.out.println(listMsg);
                for (int i = 0; i < index; i++) {
                    String taskMsg = (i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription();
                    System.out.println(taskMsg);
                }
                System.out.println();
            }
            command = sc.nextLine();
        }

        String exitMsg = "Bye. Hope to see you again soon!\n";
        System.out.println(exitMsg);
    }
}
