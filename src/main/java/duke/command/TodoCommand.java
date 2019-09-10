package duke.command;

import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class TodoCommand extends Command{

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a <code>Todo</code> task to the task list and shows a successful-adding message.
     *
     * @param taskList {@inheritDoc}
     * @param ui       {@inheritDoc}
     * @param storage  {@inheritDoc}
     * @return         a string telling a <code>Todo</code> task is added
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask = new Todo(description);
        taskList.add(newTask);
        return ui.showTaskAdded(taskList.getTotalTask(), newTask);
    }

    /**
     * Compares two <code>TodoCommand</code> objects according to their description.
     * The comparison is mainly used for JUnit tests.
     *
     * @param obj  the object to be compared
     * @return     <code>true</code> if two objects are both <code>TodoCommand<></code> and have the same description;
     *             <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        //@@author ZhangHuafan-reused
        //Reused from https://www.javaworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        //@@author
        TodoCommand another = (TodoCommand) obj;
        return this.description.equals(another.description);
    }

}
