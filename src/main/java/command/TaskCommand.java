package command;

import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.ToDo;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class TaskCommand extends Command {
    public TaskCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[]splitWords = command.trim().split("\\s",2);

            if (splitWords[0].equals("todo")) {
                ToDo.createTodo(command, tasks, storage);
            } else if (splitWords[0].equals("deadline")) {
                Deadline.createDeadline(command, tasks, storage);
            } else if (splitWords[0].equals("event")) {
                Event.createEvent(command, tasks, storage);
            } else {
                throw new DukeException("");
            }

            System.out.println("Got it. I've added this task:" + "\n" + tasks.printLatest()
                    + "\n" + "Now you have " + tasks.size() + " tasks in the list.");

        } catch (IllegalArgumentException e) {
            System.out.println("☹ OOPS!!! The date format is wrong.");
        } catch (DukeException e) {
            throw new DukeException("");
        }
    }

    /**
     * executes creating tasks.
     *
     * @param tasks is the taskList of tasks
     *
     * @param ui prints the output
     *
     * @param storage manages the output file
     *
     * @return the string output that is to be printed
     *
     * @throws DukeException when incorrect command is inputted
     */
    public String executeAsString(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[]splitWords = command.trim().split("\\s",2);

            if (splitWords[0].equals("todo")) {
                ToDo.createTodo(command, tasks, storage);
            } else if (splitWords[0].equals("deadline")) {
                Deadline.createDeadline(command, tasks, storage);
            } else if (splitWords[0].equals("event")) {
                Event.createEvent(command, tasks, storage);
            } else {
                throw new DukeException("");
            }

            return "Got it. I've added this task:" + "\n" + tasks.printLatest()
                    + "\n" + "Now you have " + tasks.size() + " tasks in the list.";

        } catch (IllegalArgumentException e) {
            return "☹ OOPS!!! The date format is wrong.";
        } catch (DukeException e) {
            throw new DukeException("");
        }
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
