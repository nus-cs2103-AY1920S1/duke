import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        ArrayList<Task> taskList = new ArrayList<>();

        greet();
        while (run && sc.hasNext()) {
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");

            switch (commandArr[0]) {
                case "list":
                    printArray(taskList);
                    break;

                case "done":
                    Task currTask = taskList.get(Integer.parseInt(commandArr[1]) - 1);
                    currTask.markAsDone();
                    taskComplete(currTask);
                    break;

                case "bye":
                    exit();
                    run = false;
                    break;

                default:
                    Task task = new Task(command);
                    taskList.add(task);
                    echo("added: " + command);
                    break;
            }
        }

        sc.close();
    }

    private static void taskComplete(Task currTask) {
        printLine();
        System.out.println("     Nice! I've marked this task as done: \n       " + currTask);
        printLine();
        System.out.println();
    }

    private static void printArray(ArrayList<Task> taskList) {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i));
        }
        printLine();
        System.out.println();
    }

    //Greet the user when starting up Duke
    private static void greet() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        printLine();
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        printLine();
        System.out.println();
    }

    // Echo commands entered by users
    private static void echo(String command) {
        printLine();
        System.out.println("     " + command);
        printLine();
        System.out.println();
    }

    //Exits when the user types bye
    private static void exit() {
        printLine();
        System.out.println("      Bye. Hope to see you again soon!");
        printLine();
        System.out.println();
    }

    // Print indented line
    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
