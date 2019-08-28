package Duke.parser;

import Duke.exception.DukeException;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.TaskList;
import Duke.task.Todo;
import Duke.ui.Ui;

import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Parser class parses all the user input.
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;
    private SimpleDateFormat formatter;

    /**
     * Constructs a new Parser object to read and process the user's input.
     * @param taskList the latest TaskList stored in the hard disk.
     */
    public Parser(TaskList taskList) {
        this.ui = new Ui();
        this.taskList = taskList;
        this.formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    /**
     * Returns a TaskList object that will contain all the remaining tasks that
     * has been entered into duke.
     * <p>
     * This method scans the commands passed into duke and processes the commands
     * accordingly.
     * @return the TaskList after the Parser processes the commands.
     */
    public TaskList start() {
        Scanner sc = new Scanner(System.in);
        boolean bye = false;

        ui.showWelcome();

        while(!bye) {
            String input = sc.nextLine();
            String[] command = input.split(" ");
            try {
                switch (command[0]) {
                case "bye":
                    ui.showBye();
                    bye = true;
                    break;
                case "list":
                    ui.showList(taskList.getTaskList());
                    break;
                case "done":
                    ui.showCompletedTask(taskList.doTask(input));
                    break;
                case "todo":
                    Todo todo = makeTodo(input);
                    taskList.addTodo(todo);
                    ui.showAddedTask(todo, taskList);
                    break;
                case "deadline":
                    Deadline deadline = makeDeadline(input);
                    taskList.addDeadline(deadline);
                    ui.showAddedTask(deadline, taskList);
                    break;
                case "event":
                    Event event = makeEvent(input);
                    taskList.addEvent(event);
                    ui.showAddedTask(event, taskList);
                    break;
                case "delete":
                    ui.showDeletedTask(taskList.deleteTask(input), taskList);
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        sc.close();
        return taskList;
    }

    /**
     * Parses the user's date input to return a Date object.
     * @param formatter the SimpleDateFormat object containing the format of the date.
     * @param input the user's date input when they specify the date of a task.
     * @return a Date object which date has been parsed into the desired format obtained
     * from the formatter.
     * @throws DukeException if the user's input is not in the right format.
     */
    public static Date parseDate(SimpleDateFormat formatter, String input) throws DukeException {
        try {
            return formatter.parse(input);
        } catch (ParseException e) {
            throw new DukeException("Please key a date in the format dd/MM/yyyy HHHH.");
        }
    }

    private Todo makeTodo(String input) throws DukeException {
        if (input.split(" ", 2).length > 1) {
            String todoInput = input.split(" ", 2)[1];
            Todo todo = new Todo(todoInput);
            return todo;
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private Deadline makeDeadline(String input) throws DukeException {
        if (input.split(" ", 2).length > 1) {
            String[] desc = input.split(" ", 2)[1].split(" /by ");
            if (desc.length > 2) {
                throw new DukeException("There are too many /by in the description.");
            } else if (desc.length < 2) {
                throw new DukeException("The description of the deadline is insufficient.");
            }
            Deadline deadline = new Deadline(desc[0], parseDate(formatter, desc[1]));
            return deadline;
        } else {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
    }

    private Event makeEvent(String input) throws DukeException {
        if (input.split(" ", 2).length > 1) {
            String[] desc = input.split(" ", 2)[1].split(" /at ");
            if (desc.length > 2) {
                throw new DukeException("There are too many /at in the description.");
            } else if (desc.length < 2) {
                throw new DukeException("The description of the deadline is insufficient.");
            }
            Event event = new Event(desc[0], parseDate(formatter, desc[1]));
            return event;
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }
}
