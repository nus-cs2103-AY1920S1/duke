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
            if (!command.equals("list") && !command.substring(0, 4).equals("done")) { //for adding new task
                if (command.substring(0, 4).equals("todo")) {
                    tasks[index] = new Todo(command);
                } else if (command.substring(0, 8).equals("deadline")) {
                    String[] arr = command.split(" /by ", 2);
                    tasks[index] = new Deadline(arr[0].substring(9), arr[1]);
                } else {
                    String[] arr = command.split(" /at ", 2);
                    tasks[index] = new Event(arr[0].substring(6), arr[1]);
                }
                String commandMsg = "Got it. I've added this task:\n"
                        + tasks[index];

                index++;
                System.out.println(commandMsg);
                String statusOfList = "Now you have " + index + " tasks in the list.\n";
                System.out.println(statusOfList);
            } else if (command.substring(0, 4).equals("done")) { //for marking task as done
                int curr = Integer.parseInt(command.substring(5));
                tasks[curr - 1].markAsDone();
                String markAsDoneMsg = "Nice! I've marked this task as done:\n" +
                        "[" + tasks[curr - 1].getStatusIcon() + "] " + tasks[curr - 1].getDescription() + "\n";
                System.out.println(markAsDoneMsg);
            } else { //for listing tasks out
                String listMsg = "Here are the tasks in your list:";
                System.out.println(listMsg);

                for (int i = 0; i < index; i++) {
                    Task task = tasks[i];
                    String taskMsg = (i + 1) + ". " + task;
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
