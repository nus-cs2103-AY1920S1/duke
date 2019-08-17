import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.runDuke();
    }

    private Duke() {
        this.list = new ArrayList<Task>();
    }

    private void runDuke() {
        printGreetingMessage();

        boolean isDone = false;
        while (!isDone) {
            String input = sc.nextLine();

            if (isExitCommand(input)) {
                isDone = true;
            } else if (isListCommand(input)) {
                printList();
            } else if (isDoneCommand(input)) {
                int oneBasedIndex = Integer.parseInt(input.replace("done ", ""));
                markTaskAsDone(oneBasedIndex);
            } else if (isAddTodoCommand(input)) {
                String todo = input.replace("todo ", "");
                addTodoToList(todo);
            } else if (isAddDeadlineCommand(input)) {
                String[] deadline = input.replace("deadline ", "").split(" /by ");
                addDeadlineToList(deadline[0], deadline[1]);
            } else if (isAddEventCommand(input)) {
                String[] event = input.replace("event ", "").split(" /at ");
                addEventToList(event[0], event[1]);
            }
        }

        printExitMessage();
    }

    private void markTaskAsDone(int oneBasedIndex) {
        int zeroBasedIndex = oneBasedIndex - 1;
        list.get(zeroBasedIndex).markAsDone();
        printMessage("Nice! I've marked this task as done:\n\t\t" + list.get(zeroBasedIndex));
    }

    private void addTodoToList(String input) {
        Todo newTodo = new Todo(input);
        list.add(newTodo);
        printSuccessMessage(newTodo);
    }

    private void addDeadlineToList(String description, String deadlineBy) {
        Deadline newDeadline = new Deadline(description, deadlineBy);
        list.add(newDeadline);
        printSuccessMessage(newDeadline);
    }

    private void addEventToList(String description, String eventTime) {
        Event newEvent = new Event(description, eventTime);
        list.add(newEvent);
        printSuccessMessage(newEvent);
    }

    private void printSuccessMessage(Task task) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        printLine();
    }

    private void printList() {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            int oneBasedIndex = i + 1;
            System.out.printf("\t%d. %s\n", oneBasedIndex, list.get(i));
        }
        printLine();
    }

    private boolean isExitCommand(String input) {
        return input.equals("bye");
    }

    private boolean isListCommand(String input) {
        return input.equals("list");
    }

    private boolean isDoneCommand(String input) {
        return input.startsWith("done ");
    }

    private boolean isAddTodoCommand(String input) {
        return input.startsWith("todo ");
    }

    private boolean isAddDeadlineCommand(String input) {
        return input.startsWith("deadline ");
    }

    private boolean isAddEventCommand(String input) {
        return input.startsWith("event ");
    }

    private void printGreetingMessage() {
        printMessage("Hello, I'm Duke\n\tWhat can I do for you?");
    }

    private void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    private void printMessage(String message) {
        printLine();
        System.out.println("\t" + message);
        printLine();
    }

    private void printLine() {
        System.out.println("\t____________________________________________________________");
    }
}