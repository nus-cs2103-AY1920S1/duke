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

            //If list print all tasks
            if (input.equals("list")) {
                System.out.print(dividerLine);

                //Print each task
                Iterator<Task> tasksIterator = tasks.iterator();
                Task currTask = new Task("");

                for (int i = 0; i < tasks.size(); i++) {
                    currTask = tasksIterator.next();

                    System.out.print("  "
                            + (i+1)
                            + ". "
                            + "["
                            + currTask.getStatusIcon()
                            + "] "
                            + currTask.getDescription()
                            + "\n");
                }

                System.out.print(dividerLine);
            } else if (inputArray[0].equals("done")) {
                int taskNum = Integer.parseInt(inputArray[1]);
                int taskIndex = taskNum - 1;

                Task modifiedTask = tasks.get(taskIndex);
                modifiedTask.markAsDone();

                System.out.print(dividerLine);

                System.out.print("  Nice! I've marked this task as done: \n");
                System.out.print("    "
                        + "["
                        + modifiedTask.getStatusIcon()
                        + "] "
                        + modifiedTask.getDescription()
                        + "\n");

                System.out.print(dividerLine);
            }

            //Else add input to array list
            //and echo that input is added
            else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.print(dividerLine
                        + "  added "
                        + input
                        + "\n"
                        + dividerLine);
            }

            input = sc.nextLine();
        }
        // Exit if bye
        System.out.print(dividerLine
                + "  Bye. Hope to see you again soon!\n"
                + dividerLine);

    }
}
