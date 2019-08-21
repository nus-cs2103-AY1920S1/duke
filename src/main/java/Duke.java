import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static List<Task> dukeList = new ArrayList<>();

    public static void main(String[] args) {
        String initialMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(initialMessage);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                displayDukeList();
            } else if (inputSplit[0].equals("done")) {
                int inputIndex = Integer.parseInt(inputSplit[1]);
                int actualListIndex = inputIndex - 1;
                Task targetTask = dukeList.get(actualListIndex);
                targetTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(targetTask);
            } else if (inputSplit[0].equals("todo")) {
                try {
                    handleInputTodo(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (inputSplit[0].equals("deadline")) {
                try {
                    handleInputDeadline(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (inputSplit[0].equals("event")) {
                try {
                    handleInputEvent(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    handleInputUnrecognised(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void displayDukeList() {
        for (int i = 0; i < dukeList.size(); i++) {
            int itemIndex = i + 1;
            String itemDisplay = itemIndex + "." + dukeList.get(i).toString();
            System.out.println(itemDisplay);
        }
    }

    public static int slashLocator(String sentence) {
        return sentence.indexOf("/");
    }

    public static void handleInputTodo(String inputTodo) throws DukeException {
        if (inputTodo.length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            String todoDescription = inputTodo.substring(5);
            Task t = new Todo(todoDescription);
            dukeList.add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + dukeList.size() + " tasks in the list.");
        }
    }

    public static void handleInputDeadline(String inputDeadline) throws DukeException {
        if (inputDeadline.length() == 8) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            int slashLocation = slashLocator(inputDeadline);
            int firstIndex = slashLocation - 1;
            int secondIndex = slashLocation + 4;
            String deadlineDescription = inputDeadline.substring(9, firstIndex);
            String deadlineBy = inputDeadline.substring(secondIndex);
            Task t = new Deadline(deadlineDescription, deadlineBy);
            dukeList.add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + dukeList.size() + " tasks in the list.");
        }
    }

    public static void handleInputEvent(String inputEvent) throws DukeException {
        if (inputEvent.length() == 5) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        } else {
            int slashLocation = slashLocator(inputEvent);
            int firstIndex = slashLocation - 1;
            int secondIndex = slashLocation + 4;
            String eventDescription = inputEvent.substring(6, firstIndex);
            String eventAt = inputEvent.substring(secondIndex);
            Task t = new Event(eventDescription, eventAt);
            dukeList.add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + dukeList.size() + " tasks in the list.");
        }
    }

    public static void handleInputUnrecognised(String inputUnrecognised) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
