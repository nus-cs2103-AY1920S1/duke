package duke.command;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import duke.command.TaskList;
import duke.command.Ui;
import duke.command.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.DukeException;

/**
 * Represents a Command manager that adds new Task to TaskList, deletes
 * tasks and printing the list of tasks.
 * Contains the fields TaskList that contains the list of Task,
 * Ui that prints the message for each commands and Storage that
 * updates the files when the list of Task is modified.
 */

public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Parser(TaskList taskList, Ui ui, Storage storage){
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parse a one line input into a Task or Command.
     * Print message is produced every time an input is passed in.
     *
     * @param input  Command to be executed or Task to be created.
     * @throws DukeException  if Task has incomplete description.
     * @throws IOException if
     */
    public void parseLineInput(String input) throws DukeException, IOException, ParseException {
        String firstWord = input.split(" ")[0];
        //ArrayList<Task> list = taskList.getList();
        if (firstWord.equals("bye")) {
            ui.printByeMessage();
        } else if (firstWord.equals("list")) {
            ui.printList(taskList.getList());
        } else if (firstWord.equals("done")) {
            Integer taskNum = Integer.valueOf(input.substring(5));
            Task currTask = taskList.getTask(taskNum - 1);
            currTask.markAsDone();
            ui.printTaskDone(currTask);
            storage.updateFile();
        } else if (firstWord.equals("todo")) {
            if (input.length() == 4) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                Task toDoTask = new Todo(input.substring(5));
                ui.printToDoTask(toDoTask, taskList.getList());
                taskList.addTask(toDoTask);
                storage.appendToFile(toDoTask);
            }
        } else if (firstWord.equals("deadline")) {
            if (input.length() == 8) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String time = input.split("/by", 2)[1];
                String description = input.split(" /by", 2)[0];
                Task deadlineTask = new Deadline(description, time);
                taskList.addTask(deadlineTask);
                ui.printDeadlineTask(deadlineTask, taskList.getList());
                storage.appendToFile(deadlineTask);
            }
        } else if (firstWord.equals("event")) {
            if (input.length() == 5) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty");
            } else {
                String time = input.split("/at", 2)[1];
                String description = input.split(" /at", 2)[0];
                Task eventTask = new Event(description, time);
                taskList.addTask(eventTask);
                ui.printEvenTask(eventTask, taskList.getList());
                storage.appendToFile(eventTask);
            }
        } else if (firstWord.equals("delete")) {
            Integer index = Integer.valueOf(input.substring(7));
            ui.printDeleteTask(taskList.getTask(index - 1), taskList.getList());
            taskList.deleteTask((int) index - 1);
            storage.updateFile();
        } else if (firstWord.equals("bye")) {
            ui.printByeMessage();
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but i don't know what that means :-(");
        }
    }
}
