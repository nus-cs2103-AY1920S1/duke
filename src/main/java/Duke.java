import java.io.FileWriter;
import java.io.IOException;
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
        readInputs(input);
    }

    public static void readInputs(Scanner input) {
        while (input.hasNext()) {
            try {
                String userInput = input.nextLine().replaceAll("\\s+", " ");
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
                    if (doneDetails.length < 2) {
                        throw new DukeException("\u2639 OOPS!!! The description of done cannot be empty.");
                    }
                    String listActionIndex = doneDetails[1];
                    if (Integer.parseInt(listActionIndex) > actions.size()) {
                        throw new DukeException("\u2639 OOPS!!! This item does not exist.");
                    }
                    int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
                    markAsDone(arrayActionIndex);
                } else if (userInput.contains("delete")) {
                    String[] deleteDetails = userInput.split(" ");
                    if (deleteDetails.length < 2) {
                        throw new DukeException("\u2639 OOPS!!! The description of delete cannot be empty.");
                    }
                    String listActionIndex = deleteDetails[1];
                    if (Integer.parseInt(listActionIndex) > actions.size()) {
                        throw new DukeException("\u2639 OOPS!!! This item does not exist.");
                    }
                    int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
                    deleteFromList(arrayActionIndex);
                }else {
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException exception) {
                printException(exception);
            }
        }
    }

    public static void printException(DukeException exception) {
        String message = exception.getMessage();
        drawLine();
        System.out.println("\t " + message);
        drawLine();
    }

    public static void writeToHardDisk() throws DukeException {
        try {
        FileWriter fileWriter = new FileWriter(
                "C:\\Users\\Yi Yin\\Documents\\Year 2\\Semester 1\\CS2103\\duke\\data\\duke.txt");
            for (int i = 0; i < actions.size(); i++) {
                fileWriter.write(actions.get(i).toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            throw new DukeException("\u2539 OOPS!!! Something went wrong :" + exception.getMessage());
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
        try {
            writeToHardDisk();
        } catch (DukeException exception) {
            printException(exception);
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

    public static void addToDo(String details) throws DukeException{
        if (details.trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        Task todo = new Todo(details.trim());
        actions.add(todo);
        printAddedTask(todo);
        try {
            writeToHardDisk();
        } catch (DukeException exception) {
            printException(exception);
        }
    }

    public static void addDeadline(String details) throws DukeException {
        String[] detailsSplit = details.split("/by");
        if (detailsSplit.length == 0 || detailsSplit[0].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        if (detailsSplit.length < 2 || detailsSplit[1].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline requires a task and/or a due date");
        }
        String action = detailsSplit[0].trim();
        String deadline = detailsSplit[1].trim();
        Task taskDeadline = new Deadline(action, deadline);
        actions.add(taskDeadline);
        printAddedTask(taskDeadline);
        try {
            writeToHardDisk();
        } catch (DukeException exception) {
            printException(exception);
        }
    }

    public static void addEvent(String details) throws DukeException{
        String[] detailsSplit = details.split( "/at");
        if (detailsSplit.length == 0 || detailsSplit[0].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
        }
        if (detailsSplit.length < 2 || detailsSplit[1].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of an event requires a task and/or a scheduled time");
        }
        String event = detailsSplit[0].trim();
        String timing = detailsSplit[1].trim();
        Task taskEvent = new Event(event, timing);
        actions.add(taskEvent);
        printAddedTask(taskEvent);
        try {
            writeToHardDisk();
        } catch (DukeException exception) {
            printException(exception);
        }
    }

    public static void markAsDone(int index) {
        Task action = actions.get(index);
        action.setDone();
        drawLine();
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.println("\t\t " + action);
        drawLine();
        try {
            writeToHardDisk();
        } catch (DukeException exception) {
            printException(exception);
        }
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
