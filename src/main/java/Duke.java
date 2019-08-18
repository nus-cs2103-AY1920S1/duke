import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> actions = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        greetUser();
        drawLine();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                sayBye();
                input.close();
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (userInput.startsWith("todo")) {
                String details = userInput.replaceFirst("todo ", "");
                addToDo(details);
            } else if (userInput.startsWith("deadline")) {
                String details = userInput.replaceFirst("deadline ", "");
                addDeadline(details);
            } else if (userInput.startsWith("event")) {
                String details = userInput.replaceFirst("event ", "");
                addEvent(details);
            } else if (userInput.contains("done")) {
                String listActionIndex = (userInput.split(" "))[1];
                int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
                markAsDone(arrayActionIndex);
            }
        }
    }

    public static void printAddedTask(Task task) {
        drawLine();
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t\t " + task);
        int numberOfTasks = actions.size();
        System.out.println("\t Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task": " tasks") + " in the list.");
        drawLine();
    }

    public static void addToDo(String details) {
        Task _todo = new Todo(details);
        actions.add(_todo);
        printAddedTask(_todo);
    }

    public static void addDeadline(String details) {
        String[] detailSplit = details.split(" /by ");
        String action = detailSplit[0];
        String deadline = detailSplit[1];
        Task _deadline = new Deadline(action, deadline);
        actions.add(_deadline);
        printAddedTask(_deadline);
    }

    public static void addEvent(String details) {
        String[] detailSplit = details.split( " /at ");
        String event = detailSplit[0];
        String timing = detailSplit[1];
        Task _event = new Event(event, timing);
        actions.add(_event);
        printAddedTask(_event);
    }

    public static void markAsDone(int index) {
        Task action = actions.get(index);
        action.setDone();
        drawLine();
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.println("\t\t " + action);
        drawLine();
    }
    public static void sayBye() {
        drawLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        drawLine();
    }

    public static void printList() {
        drawLine();
        System.out.println("\t Here are the tasks in your list: ");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println("\t " + (i + 1) + ". " + actions.get(i));
        }
        drawLine();
    }

    public static void drawLine() {
        System.out.println("\t---------------------------------------");
    }

    public static void greetUser() {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
    }
}
