import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // initialise tasks list
        tasks = new ArrayList<>();

        greet();
        getUserInput();
    }

    private static void greet() {
        String greeting = LINE
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + LINE;

        System.out.println(greeting);
    }

    private static void getUserInput() {
        String addTaskMessage = "     Got it. I've added this task: ";
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String firstWord = userInput.split("\\s")[0];
        switch (firstWord) {
            case "bye":
                String farewellMessage = LINE
                        + "     Bye. Hope to see you again soon!\n"
                        + LINE;
                System.out.print(farewellMessage);
                break;
            case "list":
                System.out.print(LINE);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task currentTask = tasks.get(i);
                    System.out.println("     " + Integer.toString(i + 1) + "." + currentTask.getTypeIcon() + currentTask.getStatusIcon() + " " + currentTask);
                }
                System.out.print(LINE);
                getUserInput();
                break;
            case "done":
                try {
                    int index = Integer.parseInt(userInput.split("\\s")[1]);
                    Task taskDone = tasks.get(index - 1);
                    taskDone.markAsDone();
                    System.out.print(LINE);
                    System.out.println("     Nice! I've marked this task as done: ");
                    System.out.println("       " + taskDone.getTypeIcon() + taskDone.getStatusIcon() + " " + taskDone);
                    System.out.print(LINE);
                    getUserInput();
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The task you want to mark as done doesn't exist.");
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            case "todo":
                try {
                    String messageTodo = userInput.split("todo ")[1];
                    if (messageTodo.trim().length() == 0) {
                        throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                    }
                    Task newTaskTodo = new Task(messageTodo, TaskType.TODO);
                    tasks.add(newTaskTodo);
                    System.out.print(LINE);
                    System.out.println(addTaskMessage);
                    System.out.println("       [T][✗] " + newTaskTodo);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            case "deadline":
                try {
                    String[] deadlineMessageAndTime = userInput.split("deadline ")[1].split(" /by ");
                    String messageDeadline = deadlineMessageAndTime[0];
                    if (messageDeadline.trim().length() == 0) {
                        throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                    }
                    if (!userInput.split("deadline ")[1].contains("/by")) {
                        throw new DukeException("A deadline requires the '/by' keyword.");
                    }
                    Task newTaskDeadline = new Task(messageDeadline, TaskType.DEADLINE);
                    Calendar deadlineCalendar = convertStringToCalendar(deadlineMessageAndTime[1]);
                    newTaskDeadline.setTime(deadlineCalendar);
                    tasks.add(newTaskDeadline);
                    System.out.print(LINE);
                    System.out.println(addTaskMessage);
                    System.out.println("       [D][✗] " + newTaskDeadline);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (DukeException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! "  + e);
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            case "event":
                try {
                    String[] eventMessageAndTime = userInput.split("event ")[1].split(" /at ");
                    String messageEvent = eventMessageAndTime[0];
                    if (messageEvent.trim().length() == 0) {
                        throw new EmptyDescriptionException("The description of an event cannot be empty.");
                    }
                    if (!userInput.split("event ")[1].contains("/at")) {
                        throw new DukeException("An event requires the '/at' keyword.");
                    }
                    Task newTaskEvent = new Task(messageEvent, TaskType.EVENT);
                    Calendar eventCalendar = convertStringToCalendar(eventMessageAndTime[1]);
                    newTaskEvent.setTime(eventCalendar);
                    tasks.add(newTaskEvent);
                    System.out.print(LINE);
                    System.out.println(addTaskMessage);
                    System.out.println("       [E][✗] " + newTaskEvent);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The description of an event cannot be empty.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (DukeException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! " + e);
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(userInput.split("\\s")[1]);
                    Task taskRemoved = tasks.remove(index - 1);
                    System.out.print(LINE);
                    System.out.println("     Noted. I've removed this task: ");
                    System.out.println("       " + taskRemoved.getTypeIcon() + taskRemoved.getStatusIcon() + " " + taskRemoved);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(LINE);
                    getUserInput();
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! The task you want to delete doesn't exist.");
                    System.out.print(LINE);
                    getUserInput();
                }
                break;
            default:
                try {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.print(LINE);
                    System.out.println("     ☹ OOPS!!! " + e);
                    System.out.print(LINE);
                    getUserInput();
                }
        }
    }

    private static Calendar convertStringToCalendar(String dateString) {
        String[] dateAndTime = dateString.split("\\s");
        String date = dateAndTime[0];
        String time = dateAndTime[1];
        int hour = Integer.parseInt(time) / 100;
        int minute = Integer.parseInt(time) % 100;

        String[] dateComponents = date.split("/");
        int day = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);

        Calendar calendar = new Calendar.Builder().setDate(year, month - 1, day)
                .setTimeOfDay(hour, minute, 0).build();

        return calendar;
    }
}