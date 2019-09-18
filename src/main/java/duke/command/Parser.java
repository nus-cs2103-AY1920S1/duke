package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a parser that will be used to parse each line of input.
 */
public class Parser {
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Parser.
     * @param taskList taskList to which the parsed Tasks would be saved
     * @param ui ui for input and output
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses the inputted string into a Task.
     * @param  str string to be parsed
     */
    public String parseLine(String str) throws DukeException, NumberFormatException, FileNotFoundException {
        assert ui != null : "Ui should not be null";
        assert taskList != null : "TaskList should not be null";

        if (str.equals("list")) {
            return parseList();
        } else if (str.equals("archive")) {
            return parseArchive();
        } else if (str.equals("list archive")) {
            return parseArchiveList();
        } else if (str.startsWith("delete ")) {
            return parseDelete(str);
        } else if (str.startsWith("done ")) {
            return parseDone(str);
        } else if (str.startsWith("find ")) {
            return parseFind(str);
        } else {
            return parseTask(str);
        }
    }

    /**
     * Parse String input as a new Task.
     * @param str input String
     * @return
     * @throws DukeException
     */
    private String parseTask(String str) throws DukeException {
        String[] arr = str.split("/", 2); // separates the the description
        String[] typeArray = arr[0].split(" ", 2); // separate the task type

        Task task;
        if (typeArray[0].equals("todo")) {
            task = parseTodo(typeArray);
        } else  if (typeArray[0].equals("deadline")) {
            task = parseDeadline(arr, typeArray);
        } else if (typeArray[0].equals("event")) {
            task = parseEvent(arr, typeArray);
        } else {
            ArrayList<String> responseArr = new ArrayList<>();
            responseArr.add("what is that");
            responseArr.add("huh");
            responseArr.add("what");
            responseArr.add("nani?");
            Random rand = new Random();
            throw new DukeException(responseArr.get(rand.nextInt(4)));
        }
        return ui.printAdd(task);
    }

    private Task parseEvent(String[] arr, String[] typeArray) throws DukeException {
        Task task;
        if (typeArray.length < 2 || typeArray[1].trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (arr.length < 2 || arr[1].trim().equals("")) {
            throw new DukeException("Events need /at description!");
        }
        if (!arr[1].startsWith("at ")) {
            throw new DukeException("Use /at  ");
        }
        task = new Event(typeArray[1], false, arr[1]);
        taskList.addTask(task);
        return task;
    }

    private Task parseDeadline(String[] arr, String[] typeArray) throws DukeException {
        Task task;
        if (typeArray.length < 2 || typeArray[1].trim().equals("")) {
            throw new DukeException("Add description to your deadline!");
        }
        if (arr.length < 2 || arr[1].trim().equals("")) {
            throw new DukeException("Add a /by description!");
        }
        if (!arr[1].startsWith("by ")) {
            throw new DukeException("Use /by  ");
        }
        task = new Deadline(typeArray[1], false, arr[1]);
        taskList.addTask(task);
        return task;
    }

    private Task parseTodo(String[] typeArray) throws DukeException {
        Task task;
        if (typeArray.length < 2  || typeArray[1].trim().equals("")) {
            throw new DukeException("Add more information about the todo!");
        }
        task = new Todo(typeArray[1], false, "");
        taskList.addTask(task);
        return task;
    }

    private String parseFind(String str) {
        String[] arr = str.split(" ", 2);
        return ui.printMatchingList(arr[1]);
    }

    private String parseDone(String str) throws DukeException {
        String[] arr = str.split(" ");
        int i = Integer.parseInt(arr[1]);
        if (i > taskList.getSize()) {
            throw new DukeException("Number can't be bigger than list size.");
        } else if (i < 1) {
            throw new DukeException("Number must be greater than 0");
        }
        return ui.printDone(taskList.markTaskAsDone(i));
    }

    private String parseDelete(String str) throws DukeException {
        String[] arr = str.split(" ");
        int i = Integer.parseInt(arr[1]);
        if (i > taskList.getSize()) {
            throw new DukeException("Number can't be bigger than list size.");
        } else if (i < 1) {
            throw new DukeException("Number must be greater than 0");
        }
        return ui.printDelete(taskList.removeTask(i));
    }

    private String parseList() {
        return ui.printList();
    }

    private String parseArchive() {
        return ui.printArchive();
    }

    private String parseArchiveList() {
        return ui.printArchiveList();
    }
}
