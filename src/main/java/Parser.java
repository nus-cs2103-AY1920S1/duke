import exception.DukeException;
import exception.EmptyDescriptionException;
import exception.WrongDateFormatException;
import task.Task;
import task.TaskList;
import task.TaskType;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Processes user's input text and executes corresponding commands.
 */
public class Parser {
    public static final String LINE = "    ____________________________________________________________\n";
    private Ui ui;

    /**
     * Returns a parser that parses user's input
     *
     * @param ui Ui object that Duke has initialised
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses user's input and executes the corresponding commands.
     *
     * @param userInput text that user sends to Duke
     */
    public void parse(String userInput) {
        String addTaskMessage = "     Got it. I've added this task: ";
        TaskList taskList = Duke.getTasks();
        ArrayList<Task> tasks = taskList.getTasks();

        String firstWord = userInput.split("\\s")[0];
        switch (firstWord) {
        case "bye":
            String farewellMessage = LINE
                    + "     Bye. Hope to see you again soon!\n"
                    + LINE;
            System.out.print(farewellMessage);
            Duke.exitDuke();
            break;
        case "list":
            System.out.print(LINE);
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                System.out.println("     " + Integer.toString(i + 1) + "." + currentTask.getTypeIcon()
                        + currentTask.getStatusIcon() + " " + currentTask);
            }
            System.out.print(LINE);
            ui.getUserInput(this);
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
                ui.getUserInput(this);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! The task you want to mark as done doesn't exist.");
                System.out.print(LINE);
                ui.getUserInput(this);
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
                ui.getUserInput(this);
            } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.print(LINE);
                ui.getUserInput(this);
            }
            break;
        case "deadline":
            try {
                String[] deadlineMessageAndTime = userInput.split("deadline ")[1].split(" /by ");
                String messageDeadline = deadlineMessageAndTime[0];
                if (messageDeadline.trim().length() == 0) {
                    throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                }
                if (!userInput.split("deadline ")[1].contains(" /by ")) {
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
                ui.getUserInput(this);
            } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                System.out.print(LINE);
                ui.getUserInput(this);
            } catch (WrongDateFormatException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! The date format must be dd/mm/yyyy hhmm.");
                System.out.print(LINE);
                ui.getUserInput(this);
            } catch (DukeException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! "  + e);
                System.out.print(LINE);
                ui.getUserInput(this);
            }
            break;
        case "event":
            try {
                String[] eventMessageAndTime = userInput.split("event ")[1].split(" /at ");
                String messageEvent = eventMessageAndTime[0];
                if (messageEvent.trim().length() == 0) {
                    throw new EmptyDescriptionException("The description of an event cannot be empty.");
                }
                if (!userInput.split("event ")[1].contains(" /at ")) {
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
                ui.getUserInput(this);
            } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! The description of an event cannot be empty.");
                System.out.print(LINE);
                ui.getUserInput(this);
            } catch (WrongDateFormatException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! The date format must be dd/mm/yyyy hhmm.");
                System.out.print(LINE);
                ui.getUserInput(this);
            } catch (DukeException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! " + e);
                System.out.print(LINE);
                ui.getUserInput(this);
            }
            break;
        case "delete":
            try {
                int index = Integer.parseInt(userInput.split("\\s")[1]);
                Task taskRemoved = tasks.remove(index - 1);
                System.out.print(LINE);
                System.out.println("     Noted. I've removed this task: ");
                System.out.println("       " + taskRemoved.getTypeIcon() + taskRemoved.getStatusIcon()
                        + " " + taskRemoved);
                System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                System.out.print(LINE);
                ui.getUserInput(this);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! The task you want to delete doesn't exist.");
                System.out.print(LINE);
                ui.getUserInput(this);
            }
            break;
        case "find":
            try {
                String textToFind = userInput.split("find ")[1].trim();
                if (textToFind.trim().length() == 0) {
                    throw new EmptyDescriptionException("The description cannot be empty.");
                }
                ArrayList<Task> matchingTasks = new ArrayList<>();
                for (Task task : tasks) {
                    if (task.getDescription().contains(textToFind)) {
                        matchingTasks.add(task);
                    }
                }

                if (matchingTasks.size() == 0) {
                    System.out.print(LINE);
                    System.out.println("     I'm sorry, but I can't find any matching tasks :-(");
                    System.out.print(LINE);
                } else {
                    System.out.print(LINE);
                    System.out.println("     Here are the matching tasks in your list:");
                    for (int i = 0; i < matchingTasks.size(); i++) {
                        Task currentTask = matchingTasks.get(i);
                        System.out.println("     " + Integer.toString(i + 1) + "." + currentTask.getTypeIcon()
                                + currentTask.getStatusIcon() + " " + currentTask);
                    }
                    System.out.print(LINE);
                }
                ui.getUserInput(this);
            } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! The description cannot be empty.");
                System.out.print(LINE);
                ui.getUserInput(this);
            }
        break;
        default:
            try {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.print(LINE);
                System.out.println("     ☹ OOPS!!! " + e);
                System.out.print(LINE);
                ui.getUserInput(this);
            }
        }
    }

    /**
     * Converts a date string in the format of dd/mm/yyyy hhmm into a <code>Calendar</code> object.
     *
     * @param dateString String in the format of dd/mm/yyyy hhmm
     * @return Calendar that represents the given date
     */
    private static Calendar convertStringToCalendar (String dateString) throws WrongDateFormatException {
        try {
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
        } catch (Exception e) {
            throw new WrongDateFormatException("Wrong date format!");
        }
    }
}