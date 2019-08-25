package duck.command;

import duck.task.Deadline;
import duck.task.Event;
import duck.task.Task;
import duck.task.Todo;
import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddCommand extends Command {

    private String taskType;
    private String description;
    private LocalDateTime dateTime;
    private LocalTime time;

    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    public AddCommand(String taskType, String description, LocalDateTime dateTime) {
        this(taskType, description);
        this.dateTime = dateTime;
    }

    public AddCommand(String taskType, String description, LocalDateTime dateTime, LocalTime time) {
        this(taskType, description, dateTime);
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        Task newTask;

        switch (taskType) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            newTask = new Deadline(description, dateTime);
            break;
        default:
            newTask = new Event(description, dateTime, time);
        }

        taskList.add(newTask);
        ui.showTaskAdded(taskList.getTotalTask(), newTask);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand another = (AddCommand) obj;
            if (taskType.equals(another.taskType)) {
                return description.equals(another.description)
                        && (dateTime == null || dateTime.equals(another.dateTime))
                        && (time == null || time.equals(another.time));
            } else {
                return false;
            }

        } else {
            return false;
        }
    }
}
