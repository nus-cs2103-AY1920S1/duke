package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;
import task.TaskType;

import java.util.Calendar;

public class AddCommand extends Command {
    private TaskType taskType;
    private String message;
    private Calendar calendar;

    public AddCommand(TaskType taskType, String message, Calendar calendar) {
        this.taskType = taskType;
        this.message = message;
        this.calendar = calendar;
    }

    public AddCommand(TaskType taskType, String message) {
        this.taskType = taskType;
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Task("placeholder", TaskType.TODO);

        switch (taskType) {
        case TODO:
            Task newTaskTodo = new Task(message, TaskType.TODO);
            tasks.add(newTaskTodo);
            task = newTaskTodo;
            break;
        case DEADLINE:
            Task newTaskDeadline = new Task(message, TaskType.DEADLINE);
            newTaskDeadline.setTime(calendar);
            tasks.add(newTaskDeadline);
            task = newTaskDeadline;
            break;
        case EVENT:
            Task newTaskEvent = new Task(message, TaskType.EVENT);
            newTaskEvent.setTime(calendar);
            tasks.add(newTaskEvent);
            task = newTaskEvent;
            break;
        }

        ui.showLine();
        ui.showAddTaskMessage();
        ui.printAddedTask(task);
        ui.println("     Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        //ui.getUserInput();
    }
}
