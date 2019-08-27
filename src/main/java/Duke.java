import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke.\nWhat can I do for you?";
        String mainMenuMessage = "To input dates and times for deadlines and events, " +
                "please use the format: 29/03/2019 6:05PM";

        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.println(mainMenuMessage);

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
            newTask = new Deadline(descriptionDeadline[0], convertInputDateTime(descriptionDeadline[1]));
            break;
        case "event":
            String[] descriptionTime = description.split(" /at ", 2);
            if (descriptionTime.length < 2) {
                throw new DukeException("Event format incorrect, should be e.g. event task /at time");
            }
            newTask = new Event(descriptionTime[0], convertInputDateTime(descriptionTime[1]));
            break;
        }

        tasks.add(newTask);

        String message = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                newTask, tasks.size()
        );
        System.out.println(message);
    }

    private static LocalDateTime convertInputDateTime(String dateTime) throws DukeException {
        // ensure that dateTime string is in the format: "12/02/2019 6:05pm"
        try {
            // get day of month, month, and year
            String[] dateArray = dateTime.split(" ")[0].split("/");
            int dayOfMonth = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);

            // get minute and hour
            String time = dateTime.split(" ")[1];
            int minute = Integer.parseInt(time.split(":")[1].substring(0, 2));
            int hour = Integer.parseInt(time.split(":")[0]);
            if (hour <= 0) {
                throw new DukeException("Hour cannot be less than or equal to 0");
            }
            boolean isPastNoon = time.substring(time.length() - 2).equalsIgnoreCase("pm");
            if (isPastNoon && hour != 12) {
                // convert to 24 hour format (except for 12pm)
                hour += 12;
            } else if (hour == 12) {
                // edge case: 12am to 12:59am
                hour = 0;
            }

            return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        } catch (Exception e) {
            System.err.println(e + "");
            throw new DukeException("DateTime \"" + dateTime + "\" format incorrect");
        }
    }
}
