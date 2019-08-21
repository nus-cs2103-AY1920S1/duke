import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<Task> list = new ArrayList<>();
    String indent = "     ";
    String input;
    String[] inputs;
    String[] inputFormatted;
    Task task;
    int output;

    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        printIntro();
        do {
            input = sc.nextLine();
            UILine();
            try {
                output = processInput(input);
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            UILine();
            System.out.println();
        }
        while (output != 1);
    }

    private int processInput(String input) throws DukeException {
        inputs = input.split(" ", 2);
        // Exit program
        switch (inputs[0]) {
            case "bye":
                System.out.println(indent + "Bye. Hope to see you again soon!");
                return 1;
            case "list":
                if (list.isEmpty()) throw new DukeException(indent + "Your list is currently empty...");
                System.out.println(indent + "Here are the tasks in your list:");
                for (Task t: list) {
                    System.out.println(indent + (list.indexOf(t) + 1) + ". " + t);
                }
                break;
            case "done":
            case "delete":
                if (inputs.length == 1)
                    throw new DukeException(indent + "☹ OOPS!!! The index of " + inputs[0] + " operation cannot be empty.");
                if (!inputs[1].matches("(0|[1-9]\\d*)"))
                    throw new DukeException(indent + "☹ OOPS!!! The index of " + inputs[0] + " operation must be a positive integer.");
                int i = Integer.parseInt(inputs[1]);
                if (i > list.size())
                    throw new DukeException(indent + "☹ OOPS!!! There is no task at index " + i + ".");

                switch (inputs[0]) {
                    case "done":
                        task = list.get(i - 1);
                        task.setDone();
                        System.out.println(indent + "Nice! I've marked this task as done: ");
                        System.out.println(indent + "  " + task);
                        break;
                    case "delete":
                        list.remove(i - 1);
                        System.out.println(indent + "Noted. I've removed this task:");
                        System.out.println(indent + "  " + task);
                        printListSize();
                        break;
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                if (inputs.length == 1) throw new DukeException(indent + "☹ OOPS!!! The description of a " + inputs[0] + " cannot be empty.");
                switch (inputs[0]) {
                    case "todo":
                        task = new Todo(inputs[1]);
                        break;
                    case "deadline":
                        if (!inputs[1].contains(" /by ")) throw new DukeException(indent + "☹ OOPS!!! The due date of a deadline cannot be empty.");
                        inputFormatted = inputs[1].split(" /by ", 2);
                        task = new Deadline(inputFormatted[0], inputFormatted[1]);
                        break;
                    case "event":
                        if (!inputs[1].contains(" /at ")) throw new DukeException(indent + "☹ OOPS!!! The duration of a event cannot be empty.");
                        inputFormatted = inputs[1].split(" /at ", 2);
                        task = new Event(inputFormatted[0], inputFormatted[1]);
                        break;
                }
                list.add(task);
                System.out.println(indent + "Got it. I've added this task:");
                System.out.println(indent + "  " + task);
                printListSize();
                break;
            default:
                throw new DukeException(indent + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return 0;
    }
    private void printIntro() {
        UILine();
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println(indent + "What can I do for you?");
        UILine();
        System.out.println();
    }
    private void UILine() {
        System.out.println("    ____________________________________________________________");
    }
    private void printListSize() {
        System.out.println(indent + "Now you have " + list.size() + " tasks in the list.");
    }
}
