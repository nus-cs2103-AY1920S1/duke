import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;


/**
 * Entry point of this project Duke. Duke is a Task manager that aims
 * to serve as an efficient way to manage our day to day tasks. It supports multiple types of task
 * such as todo reminders, tasks with a deadline and even an event.
 * The Duke task manager has many iterations, it is
 * continuously evolving and becoming smarter to cater to it users' needs.
 *
 * @author TuanDingWei
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Construct a Duke object, the Task manager bot.
     *
     * @param filePath This holds the filepath to the local storage where
     *                 a list of task is permanently stored until cleared.
     *                 The file path can be in the format of a text file.
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui.welcome();
    }

    public static void main(String[] args) {
        new Duke("/Users/TuanDingWei/Desktop/NUS_Academia" + "/CS2103/Individual_project/Duke/local/Tasks.txt").run();
    }

    /**
     * Contains most of the operations of the Task Manager bot.
     */
    protected void run() {
        String filePath = "/Users/TuanDingWei/Desktop/NUS_Academia" + "/CS2103/Individual_project/Duke/local/Tasks.txt";

        Scanner sc = new Scanner(System.in);
        String input;
        String check = "dummy";
        int taskCount;

        while (check.equals("bye") == false) {
            try {
                input = sc.nextLine();
                check = input.toLowerCase();
                if (check.equals("list")) {
                    ui.showListOfTask(tasks);
                    continue;
                }

                Parser parser = new Parser(input);
                String userCommand = parser.getUserCommand();
                String due = parser.getDue();
                String taskDescription = parser.getTaskDescription();

                if (userCommand.equals("done")) {
                    int target = Integer.valueOf(taskDescription);
                    Task taskDone;
                    if (tasks.size() >= target && target > 0) {
                        taskDone = tasks.get(target - 1);
                    } else {
                        throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                    }
                    taskDone.markAsDone();
                    storage.updateLocalFile(tasks.get());
                    ui.doneAnnouncement(taskDone);
                } else if (userCommand.equals("find")) {
                    tasks.keywordSearch(taskDescription);
                } else if (userCommand.equals("delete")) {
                    int target = Integer.valueOf(taskDescription);
                    Task taskDelete;
                    if (tasks.size() >= target && target > 0) {
                        taskDelete = tasks.get((target - 1));
                        tasks.removeTask((target - 1));
                    } else {
                        throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                    }
                    Task.reduceTaskCount();
                    storage.updateLocalFile(tasks.get());
                    taskCount = Task.getTaskCount();
                    ui.deleteAnnouncement(taskDelete, taskCount);
                } else if (!check.equals("bye")) {
                    System.out.print(createTask(userCommand, due, taskDescription, storage, ui, tasks));
                }
            } catch (DukeException ex) {
                System.out.println("OOPS!!! " + ex.getMessage() + "\n");
            }
        }

        ui.sayYourGoodBye();
    }

    /**
     * Converts user input in the form of String to the format of Date.
     * The Task Manager has the ability to read dates instead of taking dates as String.
     *
     * @param input The date/ time input in the form of String. It should follow either dd/MM/yyyy HHmm or HHmm format.
     * @return Date format of the input String.
     */
    private static Date convertStringToDate(String input) {
        Date date = new Date();
        try {
            if (input.length() <= 5) {
                SimpleDateFormat formatTimeOnly = new SimpleDateFormat("HHmm");
                date = formatTimeOnly.parse(input.trim());
                return date;
            } else {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm", Locale.ENGLISH);
                date = format.parse(input);
                return date;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Performs the operation of creating a Task (Todo, Deadline, Task).
     * It also writes the list of tasks to the local storage.
     *
     * @param userCommand     Indicates the instruction (done, delete, etc) to the bot.
     * @param due             Provides the due date/ time of the task.
     * @param taskDescription Reflects the description of a task.
     * @param storage         The local storage for the Tasks as a Storage object.
     * @param ui              The User Interface object that performs all interactions with the user.
     * @param tasks           The list of Task that are temporary stored with the bot.
     * @return Gives a feedback to the user on the operation has performed after a command is given.
     * @throws EmptyToDoDescriptionException Indicates an empty description that should not be left empty for Todo.
     * @throws EmptyDescriptionException     Indicates an empty description that should not be left empty.
     * @throws UnknownCommandException       Indicates the inability of the bot to read the command that is given by the user.
     */
    public static String createTask(String userCommand, String due, String taskDescription, Storage storage, Ui ui, TaskList tasks) throws EmptyToDoDescriptionException, EmptyDescriptionException, UnknownCommandException {

        Task t;
        String typeOfTask = "";
        if (userCommand.equals("todo")) {
            if (taskDescription.equals("dummy")) {
                throw new EmptyToDoDescriptionException("The description of a todo cannot be empty.");
            }
            t = new Todo(taskDescription);
            typeOfTask = "T";
            tasks.add(t);
            storage.writeToFile(typeOfTask, "0", taskDescription, t);
        } else if (userCommand.equals("deadline")) {
            Date dateDue = convertStringToDate(due);
            if (taskDescription.equals("dummy")) {
                throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
            }
            t = new Deadline(taskDescription, dateDue);
            tasks.add(t);
            typeOfTask = "D";
            storage.writeToFile(typeOfTask, "0", taskDescription, t);
        } else if (userCommand.equals("event")) {
            String[] eventStartEnd = due.split("-", 2);
            Date start = convertStringToDate(eventStartEnd[0]);
            Date end = convertStringToDate(eventStartEnd[1]);

            if (taskDescription.equals("dummy")) {
                throw new EmptyDescriptionException("The description of a event cannot be empty.");
            }
            t = new Event(taskDescription, start, end);
            typeOfTask = "E";
            tasks.add(t);
            storage.writeToFile(typeOfTask, "0", taskDescription, t);
        } else {
            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
        }

        int taskCount = Task.getTaskCount();
        return ui.newTaskAdded(t, taskCount);
    }
}
