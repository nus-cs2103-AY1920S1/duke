import java.util.Scanner;
import java.util.StringJoiner;
import java.util.Arrays;
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

                case "todo":
                    StringJoiner todoDescription = new StringJoiner(" ");
                    for (int i = 1; i < commandArr.length; i++) {
                        todoDescription.add(commandArr[i]);
                    }
                    Task todo = new ToDo(todoDescription.toString());
                    taskList.add(todo);
                    printTask(todo, taskList.size());
                    break;

                case "event":
                    StringJoiner atTime = new StringJoiner(" ");
                    StringJoiner eventDescription = new StringJoiner(" ");
                    int indexEvent = Arrays.asList(commandArr).indexOf("/at");

                    for (int i = indexEvent + 1; i < commandArr.length; i++) {
                        atTime.add(commandArr[i]);
                    }

                    for (int i = 1; i < indexEvent; i++) {
                        eventDescription.add(commandArr[i]);
                    }

                    Task event = new Event(eventDescription.toString(), atTime.toString());
                    taskList.add(event);
                    printTask(event, taskList.size());
                    break;

                case "deadline":
                    StringJoiner timeLimit = new StringJoiner(" ");
                    StringJoiner taskDescription = new StringJoiner(" ");
                    int index = Arrays.asList(commandArr).indexOf("/by");

                    for (int i = index + 1; i < commandArr.length; i++) {
                        timeLimit.add(commandArr[i]);
                    }

                    for (int i = 1; i < index; i++) {
                        taskDescription.add(commandArr[i]);
                    }

                    Task deadline = new Deadline(taskDescription.toString(), timeLimit.toString());
                    taskList.add(deadline);
                    printTask(deadline, taskList.size());
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

    private static void printTask(Task task, int size) {
        printLine();
        System.out.println("     Got it. I've added this task: \n       " + task);
        System.out.printf("     Now you have %d tasks in the list.\n", size);
        printLine();
        System.out.println();
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
