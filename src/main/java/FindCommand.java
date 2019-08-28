import java.text.ParseException;
import java.util.ArrayList;

public class FindCommand extends Command {

    String word;

    public FindCommand(String word) {
        this.word = word;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParseException {
        super.execute(tasks, ui, storage);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for(Task task: tasks.getTasks()) {
            if(task.find(word)) {
                matchingTasks.add(task);
            }
        }
        ui.find(matchingTasks);
    }
}
