package commands;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class for the command that adds a task into the tasklist.
 */
public class AddCommand implements Command {

    private String input;

    /**
     * Constructs the AddCommand object.
     *
     * @param ipt the string representation of the command input
     */
    public AddCommand(String ipt) {
        assert !input.equals("") : "input should not be empty";
        this.input = ipt;
    }

    /**
     * Indicates whether this is an exit command.
     *
     * @return false
     */
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Executes the add command.
     * The task will be parsed from the input string representation and added into the tail of
     * the task list.
     * The text file will be updated with the given task.
     * A completion message will be displayed after successful execution.
     *
     * @param tasks tasklist that stores all the tasks
     * @param ui ui object that deals with user interaction
     * @param storage storage object that deals with writing into the text file
     * @return string representation of the command response
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task tk = Parser.parseAddCommand(input);
            tasks.addTask(tk);
            storage.updateFile(tasks);
            return ui.showResultOfCommand("     Got it. I've added this task: \n       "
                    + tk + "\n     Now you have " + tasks.getNumOfTask() + " tasks in the list.");
        } catch (ParseException e) {
            return ui.showError("I'm sorry, but the datetime format is incorrect :-(");
        }

    }
}
