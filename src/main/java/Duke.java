import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = String.format("\n%s\n%s\n", "Hello! I'm Duke", "What can I do for you?");
        System.out.println(logo + greeting);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int numTask = 0;
        Task[] taskList = new Task[100];

        while(!input.equals("bye")) {
            if (input.matches("done\\s+\\d+")) {
                System.out.println("Nice! I've marked this task as done:");
                Task doneTask = taskList[Integer.valueOf(input.replaceAll("done\\s+", "")) - 1];
                doneTask.markAsDone();
                System.out.println(doneTask + "\n");
            } else if (!input.equals("list")) {

                if (input.matches("todo\\s+.+")) {
                    taskList[numTask] = new Todo(input.replaceAll("todo\\s+", ""));
                } else if (input.matches("deadline\\s+.+")) {
                    String taskDetails = input.replaceAll("deadline\\s+", "");
                    String[] details = taskDetails.split("\\s+/by\\s+");
                    taskList[numTask] = new Deadline(details[0], details[1]);
                } else if (input.matches("event\\s+.+")) {
                    String taskDetails = input.replaceAll("event\\s+", "");
                    String[] details = taskDetails.split("\\s+/at\\s+");
                    taskList[numTask] = new Event(details[0], details[1]);
                } else {
                    try {
                        if (input.equals("todo") || input.equals("deadline") || input.equals("event")
                                || input.equals("done")) {
                            String message = String.format("The description of a %s cannot be empty.", input);
                            throw new InsufficientArgumentException(message);
                        } else {
                            throw new InvalidArgumentException("I'm sorry, but I don't know what that means :-(");
                        }
                    } catch (DukeException exception) {

                    }
                }

                if (taskList[numTask] != null) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList[numTask]);
                    numTask++;
                    System.out.println(String.format("Now you have %d tasks in the list.\n", numTask));
                }
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numTask; i++) {
                    System.out.println(String.format("%d.%s", i + 1, taskList[i]));
                }
                System.out.println();
            }

            if (sc.hasNext()) {
                input = sc.nextLine();
            } else {
                break;
            }
        }

        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}