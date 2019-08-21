import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class Duke {
    public static void main(String[] args) {
        String dividerLine = new String("\u2501").repeat(80).concat("\n");
        String startMessage = "  Hello! I'm Duke\n"
                + "  What can I do for you?\n";
        String logo = "   ____        _        \n"
                + "  |  _ \\ _   _| | _____ \n"
                + "  | | | | | | | |/ / _ \\\n"
                + "  | |_| | |_| |   <  __/\n"
                + "  |____/ \\__,_|_|\\_\\___|\n"
                + startMessage
                + dividerLine;

        LinkedList<Task> tasks = new LinkedList<>();

        System.out.print(dividerLine + "  Hello from\n" + logo);

        //Take in input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        //Check if bye
        while (!input.equals("bye")) {
            //Split input incase it has a command and argument
            String[] inputArray = input.split(" ", 2);

            String[] cmdArgs = new String[0];
            Task newTask = new Task("");

            //If list print all tasks
            switch (inputArray[0]) {
            case "list":
                System.out.print(dividerLine);

                //Print each task
                Iterator<Task> tasksIterator = tasks.iterator();
                Task currTask = new Task("");

                for (int i = 1; i <= tasks.size(); i++) {
                    currTask = tasksIterator.next();

                    System.out.print("  "
                            + i
                            + ". "
                            + currTask
                            + "\n");
                }

                System.out.print(dividerLine);
                break;
            case "done":
                int taskNum = Integer.parseInt(inputArray[1]);
                int taskIndex = taskNum - 1;

                Task modifiedTask = tasks.get(taskIndex);
                modifiedTask.markAsDone();

                //Printing
                System.out.print(dividerLine);

                System.out.print("  Nice! I've marked this task as done: \n");
                System.out.print("    "
                        + modifiedTask
                        + "\n");

                System.out.print(dividerLine);
                break;
            case "todo":
                newTask = new Todo(inputArray[1]);
                tasks.add(newTask);
                System.out.print(dividerLine
                        + "  Got it. I've added this task:\n"
                        + "    "
                        + newTask
                        + "\n"
                        + dividerLine);
                break;
            case "event":
                cmdArgs = inputArray[1].split(" /at ", 2);
                newTask = new Event(cmdArgs[0], cmdArgs[1]);
                tasks.add(newTask);
                System.out.print(dividerLine
                        + "  Got it. I've added this task:\n"
                        + "    "
                        + newTask
                        + "\n"
                        + dividerLine);
                break;
            case "deadline":
                cmdArgs = inputArray[1].split(" /by ", 2);
                newTask = new Deadline(cmdArgs[0], cmdArgs[1]);
                tasks.add(newTask);
                System.out.print(dividerLine
                        + "  Got it. I've added this task:\n"
                        + "    "
                        + newTask
                        + "\n"
                        + dividerLine);
                break;
            default:
                System.out.println("Invalid Command");
                break;
            }

            input = sc.nextLine();
        }
        // Exit if bye
        System.out.print(dividerLine
                + "  Bye. Hope to see you again soon!\n"
                + dividerLine);

    }
}
