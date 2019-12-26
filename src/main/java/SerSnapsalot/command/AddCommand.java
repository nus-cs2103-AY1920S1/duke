package SerSnapsalot.command;

import SerSnapsalot.exception.DukeException;
import SerSnapsalot.storage.Storage;
import SerSnapsalot.task.Deadline;
import SerSnapsalot.task.Event;
import SerSnapsalot.task.Task;
import SerSnapsalot.task.ToDo;
import SerSnapsalot.taskList.TaskList;
import SerSnapsalot.ui.Ui;

/**
 * Represents an adding to task list command.
 * Commands include adding Deadlines, Events and ToDos to the task list.
 */
public class AddCommand extends Command {

	public AddCommand(String type, String command) {
        super(type, command);
    }

    /**
     * Executes the command.AddCommand.
     * Adds Deadlines: "deadline".
     * Adds Events: "event".
     * Adds ToDos: "todo".
     *
     * @param ui       The Ui currently running.
     * @param taskList The TaskList Class containing the task list.
     * @param storage  The Storage class containing the name of file the be read.
     * @return output The String output for GUI message.
     * @throws DukeException If unable to identify command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task newTask;
        if (type.equals("deadline") || type.equals("event")) {
            if (type.equals("deadline")) {
                newTask = new Deadline(command);
            } else {
                assert (type.equals("event"));
                newTask = new Event(command);
            }
            try {
                newTask.understandDate();
            } catch (Exception e) {
                System.out.println(e);
            }
            TaskList.tasks.add(newTask);
        } else if (this.type.equals("todo")) {
            newTask = new ToDo(command);
            TaskList.tasks.add(newTask);
        } else {
            //Should not reach this part of code.
            assert true : command;
            throw new DukeException("AddCommand Exception: Unable to identify command");
        }

        String output = "Got it kid. I've added this task:\n" + newTask + "\n";
        output += "Now you have " + TaskList.tasks.size() + " tasks left\n";
        output += "Better get on with it before I take away your suit.";
        return output;
    }
}
