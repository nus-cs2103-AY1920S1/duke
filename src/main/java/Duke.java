import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String input;
    private static boolean running;
    private static List<Task> list;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        setupResources();
        greet();
        while (running) {
            readInput();
            processInput();
        }
    }

    public static void setupResources() {
        running = true;
        list = new ArrayList<>();
    }

    public static void greet() {
        running = true;
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hello! I'm Duke\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________\n");
    }

    public static void sayGoodbye() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________"
        );
        running = false;
    }

    public static void addTaskToList() {
        Task taskToAdd = null;
        String checkType[] = input.split(" ", 2);
        String typeOfTask = checkType[0];
        String theTask = checkType[1];
        // switch statement for todo, deadline, task
        switch(typeOfTask) {
            case "todo":
                taskToAdd = new ToDo(theTask);
                list.add(taskToAdd);
                break;

            case "deadline":
                String taskByWhen[] = theTask.split(" /", 2);
                String deadlineTask = taskByWhen[0];
                String byWhen = taskByWhen[1];
                taskToAdd = new Deadline(deadlineTask, byWhen);
                list.add(taskToAdd);
                break;

            case "event":
                String taskAtTime[] = theTask.split(" /", 2);
                String eventTask = taskAtTime[0];
                String atTime = taskAtTime[1];
                taskToAdd = new Event(eventTask, atTime);
                list.add(taskToAdd);
                break;

            default:
                break;
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + taskToAdd);
        System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
        System.out.println("    ____________________________________________________________");
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("     %d. %s", i+1, list.get(i)));
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void readInput() {
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
    }

    public static void markAsDone(int positionInList) {
        Task taskToMark = list.get(positionInList - 1);
        taskToMark.done();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println(String.format("     %s", taskToMark));
        System.out.println("    ____________________________________________________________");
    }

    public static void processInput() {
        Scanner scanner = new Scanner(input);
        String toProcess = scanner.next();

        switch (toProcess) {
            case "list":
                printList();
                break;

            case "bye":
                sayGoodbye();
                break;

            case "done":
                markAsDone(scanner.nextInt());
                break;

            default:
                addTaskToList();
        }
    }
}
