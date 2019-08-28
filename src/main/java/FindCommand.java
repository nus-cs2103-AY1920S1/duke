import java.util.ArrayList;

public class FindCommand extends Command {
    private String description;

    public FindCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the matching tasks in your list:");
        ArrayList<Task> matchingTasks = new ArrayList<>();
        int count = 1;
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(description)) {
                matchingTasks.add(task);
            }
        }
        for (Task task : matchingTasks) {
            System.out.println(count + "." + task);
            count++;
        }
    }
}
