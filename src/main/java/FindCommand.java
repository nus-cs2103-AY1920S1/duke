import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String[] oneLine;
    private List<Task> myList = new ArrayList<Task>();

    public FindCommand(String[] oneLine) {
        this.oneLine = oneLine;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String partTaskName = oneLine[1].trim();
        for (Task t : tasks.getTaskList()) {
            if (t.description.contains(partTaskName)) {
                myList.add(t);
            }
        }
        int i = 0;
        if (myList.size() != 0) {
            System.out.println(Ui.frontSpace + " Here are the matching tasks in your list: ");
            for (Task tk : myList) {
                i++;
                System.out.println(Ui.frontSpace + " " + i + ". " + tk);
            }
            i = 0;
        } else {
            throw new TaskNotExistException("task does not exist");
        }
    }
}