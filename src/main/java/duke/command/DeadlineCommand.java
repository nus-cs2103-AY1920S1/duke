package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command{

    private String description;
    private LocalDateTime dueDateTime;

    public DeadlineCommand(String description, LocalDateTime dueDateTime) {
        this.description = description;
        this.dueDateTime = dueDateTime;
    }

    /**
     * Adds a <code>Deadline</code> task to the task list and shows a successful-adding message.
     *
     * @param taskList {@inheritDoc}
     * @param ui       {@inheritDoc}
     * @param storage  {@inheritDoc}
     * @return         a string telling <code>Deadline</code> task is added
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask = new Deadline(description, dueDateTime);
        taskList.add(newTask);
        try {
            storage.recordTasks(taskList);
        } catch (IOException e) {
            return ui.showSavingError();
        }
        return ui.showTaskAdded(taskList.getTotalTask(), newTask);
    }

    /**
     * Compares two <code>DeadlineCommand</code> objects according to their description, dateTime.
     * The comparison is mainly used for JUnit tests.
     *
     * @param obj the object to be compared
     * @return    <code>true</code> if two objects are both <code>DeadlineCommand<></code>, and have
     *            the same description and dateTime;
     *            <code>false</code> otherwise.
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
        DeadlineCommand another = (DeadlineCommand) obj;
        boolean isSameDescription = this.description.equals(another.description);
        boolean isSameDueDateTime = this.dueDateTime.equals(another.dueDateTime);
        return  isSameDescription && isSameDueDateTime;
    }
}
