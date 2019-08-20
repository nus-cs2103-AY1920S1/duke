import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelSix {
    private List<Task> list;
    // Main function
    public void run() {
        greet();
        list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.next(); // Initial Input
        while (!input.equals("bye")) {
            switch (input) {
                case "list":
                    echoList(list);
                    break;
                case "done":
                    completeTask(sc.nextInt());
                    break;
                case "delete":
                    deleteTask(sc.nextInt());
                    break;
                case "todo":
                    createTodo(sc.nextLine());
                    break;
                case "deadline":
                    createDeadline(sc.nextLine());
                    break;
                case "event":
                    createEvent(sc.nextLine());
                    break;
                default:
                    sc.nextLine();
                    handleDefault();

            }
            input = sc.next();
        }
        exit();
    }

    // Helper Functions
    private void createTodo(String input) {
        try {
            if (input.isEmpty())
                throw new DukeException("The description of a todo cannot be empty.");
            Task newTodo = new Todo(input);
            list.add(newTodo);
            echoEntry(newTodo.toString());
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());

        }
    }

    private void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    private void echoEntry(String input) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                String.format("       %s \n", input) +
                String.format("     Now you have %d tasks in the list. \n", list.size()) +
                "    ____________________________________________________________");
    }

    private void echoList(List<Task> list) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("     %d.%s",
                    i+1, list.get(i).toString()));
        }
        System.out.println("    ____________________________________________________________");
    }

    private void completeTask(int entryNumber) {
        list.get(entryNumber - 1).setDone();
        System.out.println(String.format("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       %s\n" +
                "    ____________________________________________________________",
                list.get(entryNumber - 1).toString()));
    }

    private void deleteTask(int entryNumber) {
        System.out.println(String.format("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "       %s\n" +
                "     Now you have %d tasks in the list.\n" +
                "    ____________________________________________________________",
                list.remove(entryNumber - 1).toString(), list.size()));
    }

    private void createDeadline(String input) {
        try {
            String[] parsedDeadline = input.split(" \\/by ");
            if (input.isEmpty())
                throw new DukeException("The description of a deadline cannot be empty.");
            if (parsedDeadline.length == 1)
                throw new DukeException("Deadline is missing a deadline");
            Task newDeadline = new Deadline(parsedDeadline[0], parsedDeadline[1]);
            list.add(newDeadline);
            echoEntry(newDeadline.toString());
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());

        }
    }

    private void createEvent(String input) {
        try {
            String[] parsedEvent = input.split(" \\/at ");
            if (input.isEmpty())
                throw new DukeException("The description of an event cannot be empty.");
            if (parsedEvent.length == 1)
                throw new DukeException("Event is missing a location");
            Task newEvent = new Event(parsedEvent[0], parsedEvent[1]);
            list.add(newEvent);
            echoEntry(newEvent.toString());

        }
        catch (DukeException e) {
            System.out.println(e.getMessage());

        }
    }

    private void handleDefault() {
        System.out.println(new DukeException().getMessage());
    }

    private void exit() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
