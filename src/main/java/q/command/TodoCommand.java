package q.command;

import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;
import q.task.Task;
import q.task.Todo;

/**
 * Handles adding a Todo.
 */
public class TodoCommand extends AddCommand {
    private String description;

    /**
     * Constructs a TodoCommand.
     *
     * @param description The description of the Todo.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a Todo of description to tasks.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String representing the Todo.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks);
        return ui.showAddTask(todo, tasks.getSize());
    }
}
