package duke;

import task.Task;
import task.TaskList;
import task.Todo;
import task.Deadline;
import task.Event;

import Exception.DukeException;

public class Command {
    private DukeEnum de;
    private boolean isExit;
    private String description;

    /**
     * Creates a duke.Command object.
     * @param de enum object.
     * @param description of the command.
     */
    public Command(DukeEnum de, String description) {
        this.de = de;
        isExit = de.equals(DukeEnum.BYE);
        this.description = description;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command accordingly.
     * @param tasks refers to all the tasks.
     * @param ui refers to the object that handles all the things to do with user.
     * @param storage refers to the storage object.
     * @throws DukeException when an error occurred.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (de) {
        case LIST:
            ui.setText(tasks.toString());
            break;
        case BYE:
            ui.showBye();
            break;
        case TODO:
            Task task;
            task = new Todo(description);
            tasks.addTask(task);
            storage.writeToFile(tasks.getTasks());
            ui.showTaskSaved(task, tasks.getSize());
            break;
        case DEADLINE:
            task = new Deadline(description);
            tasks.addTask(task);
            storage.writeToFile(tasks.getTasks());
            ui.showTaskSaved(task, tasks.getSize());
            break;
        case EVENT:
            task = new Event(description);
            tasks.addTask(task);
            storage.writeToFile(tasks.getTasks());
            ui.showTaskSaved(task, tasks.getSize());
            break;
        case DONE:
            try {
                int i = Integer.parseInt(description) - 1;
                tasks.done(i);
                storage.writeToFile(tasks.getTasks());

                String nice = "Nice! I've marked this task as done:\n\t" + tasks.getTask(i);
                ui.setText(nice);
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("I don't think that task exists");
            } finally {
                break;
            }
        case DELETE:
            int i = Integer.parseInt(description) - 1;
            task = tasks.deleteTask(i);
            storage.writeToFile(tasks.getTasks());
            String noted = "Noted. I've removed this task:\n\t" + task
                    + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            ui.setText(noted);
            break;
        case FIND:
            ui.setText(tasks.findTask(description).toString());
            break;
        default:
            break;
        }
    }
}
