import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddCommand extends Command {

    String taskType;
    String description;
    LocalDateTime dateTime;
    LocalTime time;

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
}
