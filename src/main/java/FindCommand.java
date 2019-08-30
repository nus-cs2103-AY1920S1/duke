import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String description) {
        super();
        desc = description;
    }

    public void execute(TaskList task, Ui ui, Storage storage) {
        String pattern = String.format(".*%s.*", desc);
        TaskList tempTaskList = new TaskList();
        for (Task t : task.getTaskList()) {
            if (t.getDescription().matches(pattern)) {
                tempTaskList.addTask(t);
            }
        }
        ui.showText(tempTaskList.printMatchingTasks());
    }
}
