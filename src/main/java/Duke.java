import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        String input;
        String command;

        while (true) {
            input = sc.nextLine();
            command = input.split(" ")[0];
            if (command.equalsIgnoreCase("bye")) {
                break;
            }

            switch (command) {
                case "list":
                    String task;
                    // print all tasks in list
                    System.out.println("Here are the task(s) in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        task = String.format("%d.%s", (i + 1), tasks.get(i));
                        System.out.println(task);
                    }
                    if (tasks.size() == 0) {
                        System.out.println("There are no tasks in your list right now.");
                    }
                    break;
                case "done":
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(input.split(" ")[1]);
                    } catch (Exception e) {
                        System.out.println("Error: Please indicate the task's number to mark as done. e.g. 'done 1'");
                        continue;
                    }
                    try {
                        tasks.get(taskNumber - 1).markDone();
                    } catch (Exception e) {
                        System.out.println("Error: Task number invalid.");
                        continue;
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("  %s", tasks.get(taskNumber - 1)));
                    break;
                case "todo":
                case "deadline":
                case "event":
                    String taskDescription;
                    try {
                        taskDescription = input.split(" ", 2)[1];
                    } catch (Exception e) {
                        System.out.println("Error: The description of a " + command + " cannot be empty.");
                        continue;
                    }
                    try {
                        AddTask(command, taskDescription, tasks);
                    } catch (Exception e) {
                        System.out.println(e);
                        continue;
                    }
                    break;
                default:
                    System.out.println("I'm sorry, but I don't know what that means :(");
                    break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void AddTask(String type, String description, ArrayList<Task> tasks) throws DukeException {
        Task newTask = null;

        switch (type) {
            case "todo":
                newTask = new ToDo(description);
                break;
            case "deadline":
                String[] descriptionDeadline = description.split(" /by ", 2);
                if (descriptionDeadline.length < 2) {
                    throw new DukeException("Deadline format incorrect, should be e.g. do /by time");
                }
                newTask = new Deadline(descriptionDeadline[0], descriptionDeadline[1]);
                break;
            case "event":
                String[] descriptionTime = description.split(" /at ", 2);
                if (descriptionTime.length < 2) {
                    throw new DukeException("Event format incorrect, should be e.g. event /at time");
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
