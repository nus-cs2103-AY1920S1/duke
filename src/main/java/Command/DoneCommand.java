package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;
import duke.task.Task;


public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand which inherits from Command.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public DoneCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Marks as done the aforementioned task from TaskList based on the number parsed from inputcommand.
     * public method called by Duke.run() in the for loop as part of the Command Pattern.
     * Needs to be implemented as inherited from Command abstract method.
     * Uses Storage Class to write to file duke.txt, Ui class to print responses and TaskList to store the arraylist.
     *
     * @param tasks TaskList passed from the Duke main class, containing Array of Tasks.
     * @param ui Ui passed from the Duke main class, responsible for printing output to the user and obtaining input.
     * @param storage Storage passed from the Duke main class, responsible for updating duke.txt after every command.
     * @throws DukeException which can come from two sources, itself when the description of done isn't complete
     * or when there is an invalid value for done command or if the task is already done,or the Storage Class.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = this.inputCommand.split(" ", 2);
        if (inputsplit.length <= 1) {
            throw new DukeException("OOPS!!! The description of done must have a value.");
        } else if (Integer.parseInt(inputsplit[1]) > tasks.getSize() || Integer.parseInt(inputsplit[1]) <= 0) {
            throw new DukeException("OOPS!!! Invalid value for task done!");
        } else if (tasks.getTask(Integer.parseInt(inputsplit[1])).getIsDone() == true) {
            throw new DukeException("OOPS!!! Task is already done!");
        } else {
            int num = Integer.parseInt(inputsplit[1]);
            Task t =  tasks.getTask(num);
            t.markIsDone();
            ui.printDone(t);
            storage.writeToFile(tasks.getTaskList());
        }
    }

}
