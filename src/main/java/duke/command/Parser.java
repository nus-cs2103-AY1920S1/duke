package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a parser that will be used to parse each line of input
 */
public class Parser {
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Parser
     * @param taskList taskList to which the parsed Tasks would be saved
     * @param ui ui for input and output
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses the inputted string into a Task
     * @param  str string to be parsed
     */
    public void parseLine(String str) throws DukeException, NumberFormatException{
        if (str.equals("list")) {
            ui.printList();
        } else if (str.startsWith("delete ")) {
            String[] arr = str.split(" ");
            int i = Integer.parseInt(arr[1]);
            if (i > taskList.getSize()) {
                throw new DukeException("Number can't be bigger than list size.");
            } else if (i < 1) {
                throw new DukeException("Number must be greater than 0");
            }
            ui.printDelete( taskList.removeTask(i));
        } else if (str.startsWith("done ")) {
            String[] arr = str.split(" ");
            int i = Integer.parseInt(arr[1]);
            if (i > taskList.getSize()) {
                throw new DukeException("Number can't be bigger than list size.");
            } else if (i < 1) {
                throw new DukeException("Number must be greater than 0");
            }
            ui.printDone(taskList.markTaskAsDone(i));
        } else {
            String[] arr = str.split("/", 2); // separates the the description
            String[] typeArray = arr[0].split(" ", 2); // separate the task type

            Task task;
            if (typeArray[0].equals("todo")) {
                if (typeArray.length < 2  || typeArray[1].trim().equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new Todo(typeArray[1], false, "");
                taskList.addTask(task);
            } else  if (typeArray[0].equals("deadline")) {
                if (typeArray.length < 2 || typeArray[1].trim().equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                if (arr.length < 2 || arr[1].trim().equals("")) {
                    throw new DukeException("☹ OOPS!!! The time of a deadline cannot be empty, add a /by description!");
                }
                if (!arr[1].startsWith("by ")) {
                    throw new DukeException("Use /by  ");
                }
                task = new Deadline(typeArray[1], false, arr[1]);
                taskList.addTask(task);
            } else if (typeArray[0].equals("event")){
                if (typeArray.length < 2 || typeArray[1].trim().equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                if (arr.length < 2 || arr[1].trim().equals("")) {
                    throw new DukeException("☹ OOPS!!! The time of a deadline cannot be empty, add a /at description!");
                }
                if (!arr[1].startsWith("at ")) {
                    throw new DukeException("Use /at  ");
                }
                task = new Event(typeArray[1], false, arr[1]);
                taskList.addTask(task);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            ui.printAdd(task);
        }
    }
}
