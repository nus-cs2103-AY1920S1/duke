import java.util.ArrayList;
import java.util.Arrays;
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
            try {
                readInput();
                processInput();
            } catch (Exception e) {
                handleException(e);
            }
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

    public static void readInput() {
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
    }

    public static void processInput() throws InvalidInputDukeException, EmptyTaskDukeException, InvalidTaskDukeException{
        Scanner scanner = new Scanner(input);
        if (scanner.hasNext()) {
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

                case "delete":
                    removeTaskFromList(scanner.nextInt());
                    break;

                case "todo":
                case "deadline":
                case "event":
                    addTaskToList();
                    break;

                default:
                    throw new InvalidInputDukeException();
            }
        } else {
            throw new InvalidInputDukeException();
        }
    }

    public static void addTaskToList() throws EmptyTaskDukeException, InvalidTaskDukeException {
        Task taskToAdd = null;
        String checkType[] = Arrays.copyOf(input.split(" ", 2), 2);
        String typeOfTask = checkType[0];
        String theTask = checkType[1];
        // switch statement for todo, deadline, task
        switch(typeOfTask) {
            case "todo":
                taskToAdd = new ToDo(theTask);
                list.add(taskToAdd);
                break;

            case "deadline":
                String taskByWhen[];
                // hardcode exception first
                if (theTask == null) {
                    taskByWhen = new String[] {null, null};
                } else {
                    taskByWhen = Arrays.copyOf(theTask.split(" /by ", 2), 2);
                }
                String deadlineTask = taskByWhen[0];
                String byWhen = taskByWhen[1];
                taskToAdd = new Deadline(deadlineTask, byWhen);
                list.add(taskToAdd);
                break;

            case "event":
                String taskAtTime[];
                if (theTask == null) {
                    taskAtTime = new String[] {null, null};
                } else {
                    taskAtTime = Arrays.copyOf(theTask.split(" /at ", 2), 2);
                }
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

    public static void removeTaskFromList(int positionInList) {
        Task removedTask = list.remove(positionInList - 1);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: ");
        System.out.println(removedTask);
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

    public static void markAsDone(int positionInList) {
        Task taskToMark = list.get(positionInList - 1);
        taskToMark.done();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println(String.format("     %s", taskToMark));
        System.out.println("    ____________________________________________________________");
    }

    public static void handleException(Exception e) {
        if (e instanceof InvalidInputDukeException) {
            System.out.println("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________");
        } else if (e instanceof EmptyTaskDukeException) {
            System.out.println(String.format("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a %s cannot be empty.\n" +
                    "    ____________________________________________________________", e.getMessage()));
        } else if (e instanceof InvalidTaskDukeException) {
            System.out.println(String.format("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! Invalid input! Make sure your %s has a description and /at or /by.\n" +
                    "    ____________________________________________________________", e.getMessage()));
        } else {
            System.out.println(e.getMessage()); // for undeclared exceptions
        }

    }

    public static void sayGoodbye() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________"
        );
        running = false;
    }
}
