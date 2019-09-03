package duke.parser;

import duke.exception.DukeException;
import duke.exception.DukeParserException;
import duke.exception.DukeTaskException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

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
     *
     * @param taskList the latest TaskList stored in the hard disk.
     */
    public Parser(TaskList taskList) {
        this.ui = new Ui();
        this.taskList = taskList;
        this.formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    /**
     * Parses the user input passed into duke and processes it accordingly.
     *
     * <p>After parsing each input, a boolean of whether to exit is returned.
     *
     * @return whether the bye command is given to exit from Duke.
     */
    public boolean parse(String input) {
        String[] command = input.split(" ");

        switch (command[0]) {
        case "bye":
            ui.showBye();
            return true;
        case "list":
            ui.showList(taskList.getTaskList());
            return false;
        case "done":
            try {
                Task doneTask = taskList.doTask(input);
                ui.showCompletedTask(doneTask);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        case "todo":
            try {
                Todo todo = makeTodo(input);
                taskList.addTask(todo);
                ui.showAddedTask(todo, taskList);
            } catch (DukeTaskException e) {
                System.out.println(e.getMessage());
            }
            return false;
        case "deadline":
            try {
                Deadline deadline = makeDeadline(input);
                taskList.addTask(deadline);
                ui.showAddedTask(deadline, taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        case "event":
            try {
                Event event = makeEvent(input);
                taskList.addTask(event);
                ui.showAddedTask(event, taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        case "delete":
            try {
                Task deletedTask = taskList.deleteTask(input);
                ui.showDeletedTask(deletedTask, taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        case "find":
            TaskList foundList = taskList.findTask(input);
            ui.showFound(foundList);
            return false;
        default:
            try {
                throw new DukeParserException("I'm sorry, but I don't know what that means :-(");
            } catch (DukeParserException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
    }

    /**
     * Parses the user's date input to return a Date object.
     *
     * @param formatter the SimpleDateFormat object containing the format of the date.
     * @param input the user's date input when they specify the date of a task.
     * @return a Date object which date has been parsed into the desired format obtained from the formatter.
     * @throws DukeException if the user's input is not in the right format.
     */
    public static Date parseDate(SimpleDateFormat formatter, String input) throws DukeParserException {
        try {
            return formatter.parse(input);
        } catch (ParseException e) {
            throw new DukeParserException("Please key a date in the format dd/MM/yyyy HHHH.");
        }
    }

    private Todo makeTodo(String input) throws DukeTaskException {
        if (input.split(" ", 2).length > 1) {
            String todoInput = input.split(" ", 2)[1];
            Todo todo = new Todo(todoInput);
            return todo;
        } else {
            throw new DukeTaskException("The description of a todo cannot be empty.");
        }
    }

    private Deadline makeDeadline(String input) throws DukeException {
        if (input.split(" ", 2).length > 1) {
            String[] desc = input.split(" ", 2)[1].split(" /by ");
            if (desc.length > 2) {
                throw new DukeTaskException("There are too many /by in the description.");
            } else if (desc.length < 2) {
                throw new DukeTaskException("The description of the deadline is insufficient.");
            }
            Deadline deadline = new Deadline(desc[0], parseDate(formatter, desc[1]));
            return deadline;
        } else {
            throw new DukeTaskException("The description of a deadline cannot be empty.");
        }
    }

    private Event makeEvent(String input) throws DukeException {
        if (input.split(" ", 2).length > 1) {
            String[] desc = input.split(" ", 2)[1].split(" /at ");
            if (desc.length > 2) {
                throw new DukeTaskException("There are too many /at in the description.");
            } else if (desc.length < 2) {
                throw new DukeTaskException("The description of the deadline is insufficient.");
            }
            Event event = new Event(desc[0], parseDate(formatter, desc[1]));
            return event;
        } else {
            throw new DukeTaskException("The description of a todo cannot be empty.");
        }
    }
}
