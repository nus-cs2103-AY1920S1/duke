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
            try {
                String userInput = input.nextLine();
                if (userInput.equals("bye")) {
                    sayBye();
                    input.close();
                    break;
                } else if (userInput.equals("list")) {
                    printList();
                } else if (userInput.startsWith("todo")) {
                    String details = userInput.replaceFirst("todo", "");
                    addToDo(details);
                } else if (userInput.startsWith("deadline")) {
                    String details = userInput.replaceFirst("deadline", "");
                    addDeadline(details);
                } else if (userInput.startsWith("event")) {
                    String details = userInput.replaceFirst("event", "");
                    addEvent(details);
                } else if (userInput.contains("done")) {
                    String[] doneDetails = userInput.split(" ");
                    if (doneDetails.length == 1) {
                        throw new DukeException("\u2639 OOPS!!! The description of done cannot be empty.");
                    }
                    String listActionIndex = doneDetails[1];
                    int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
                    markAsDone(arrayActionIndex);
                } else if (userInput.contains("delete")) {
                    String[] deleteDetails = userInput.split(" ");
                    if (deleteDetails.length == 1) {
                        throw new DukeException("\u2639 OOPS!!! The description of delete cannot be empty.");
                    }
                    String listActionIndex = deleteDetails[1];
                    int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
                    deleteFromList(arrayActionIndex);
                }else {
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException exception) {
                String message = exception.getMessage();
                drawLine();
                System.out.println("\t " + message);
                drawLine();
            }
        }
    }

    public static void deleteFromList(int index) {
        Task action = actions.remove(index);
        drawLine();
        System.out.println("\t Noted. I've removed this task: ");
        System.out.println("\t\t " + action);
        int numberOfTasks = actions.size();
        System.out.println("\t Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task": " tasks") + " in the list.");
        drawLine();
    }

    public static void printAddedTask(Task task) {
        drawLine();
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t\t " + task);
        int numberOfTasks = actions.size();
        System.out.println("\t Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task": " tasks") + " in the list.");
        drawLine();
    }

    public static void addToDo(String details) throws DukeException{
        if (details.length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        Task _todo = new Todo(details.trim());
        actions.add(_todo);
        printAddedTask(_todo);
    }

    public static void addDeadline(String details) throws DukeException {
        String[] detailSplit = details.split(" /by ");
        if (detailSplit.length == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        if (detailSplit.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline requires a due date.");
        }
        String action = detailSplit[0].trim();
        String deadline = detailSplit[1].trim();
        Task _deadline = new Deadline(action, deadline);
        actions.add(_deadline);
        printAddedTask(_deadline);
    }

    public static void addEvent(String details) throws DukeException{
        String[] detailSplit = details.split( " /at ");
        if (detailSplit.length == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
        }
        if (detailSplit.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of an event requires a timing.");
        }
        String event = detailSplit[0].trim();
        String timing = detailSplit[1].trim();
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
        System.out.println("\t-----------------------------------------------------------------------------");
    }

    public static void greetUser() {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
    }
}
