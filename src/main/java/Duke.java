import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            String command = input.split(" ")[0].toLowerCase();

            if (command.equalsIgnoreCase("bye")) {
                break;
            }

            try {
                switch (command) {
                case "list":
                    PrintTasks(tasks);
                    break;
                case "done":
                    MarkTaskDone(tasks, input);
                    break;
                case "delete":
                    DeleteTask(tasks, input);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    AddTask(tasks, input, command);
                    break;
                default:
                    System.out.println("I'm sorry, but I don't know what that means :(");
                    break;
                }
            } catch (DukeException e) {
                System.err.println("" + e);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void PrintTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the task(s) in your list:");

        String task;
        for (int i = 0; i < tasks.size(); i++) {
            task = String.format("%d.%s", (i + 1), tasks.get(i));
            System.out.println(task);
        }

        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list right now.");
        }
    }

    private static void MarkTaskDone(ArrayList<Task> tasks, String input) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.split(" ")[1]);
            tasks.get(taskNumber - 1).markDone();
        } catch (Exception e) {
            throw new DukeException("Invalid task number.");
        }
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", tasks.get(taskNumber - 1)));
    }

    private static void DeleteTask(ArrayList<Task> tasks, String input) throws DukeException {
        int taskNumber;
        Task deletedTask;
        try {
            taskNumber = Integer.parseInt(input.split(" ")[1]);
            deletedTask = tasks.remove(taskNumber - 1);
        } catch (Exception e) {
            throw new DukeException("Invalid task number.");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format("  %s", deletedTask));
        System.out.println(String.format("Now you have %d task(s) in the list.", tasks.size()));
    }

    private static void AddTask(ArrayList<Task> tasks, String input, String type) throws DukeException {
        // get task description
        String description;
        try {
            description = input.split(" ", 2)[1];
        } catch (Exception e) {
            throw new DukeException("Description of " + type + " cannot be empty.");
        }

        Task newTask = null;

        // create new task of specified type
        switch (type) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            String[] descriptionDeadline = description.split(" /by ", 2);
            if (descriptionDeadline.length < 2) {
                throw new DukeException("Deadline format incorrect, should be e.g. deadline task /by time");
            }
            newTask = new Deadline(descriptionDeadline[0], descriptionDeadline[1]);
            break;
        case "event":
            String[] descriptionTime = description.split(" /at ", 2);
            if (descriptionTime.length < 2) {
                throw new DukeException("Event format incorrect, should be e.g. event task /at time");
            }
            newTask = new Event(descriptionTime[0], descriptionTime[1]);
            break;
        }

        tasks.add(newTask);

        String message = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                newTask, tasks.size()
        );
        System.out.println(message);
    }
}
