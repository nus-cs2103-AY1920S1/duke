import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String[] oneLine;
    private List<Task> myList = new ArrayList<>();

    public FindCommand(String[] oneLine) {
        this.oneLine = oneLine;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String partTaskName = oneLine[1].trim();
        for (Task t : tasks.getTaskList()) {
            if (t.description.contains(partTaskName)) {
                myList.add(t);
            }
        }
        int i = 0;
        if (myList.size() != 0) {
            System.out.println(Ui.frontSpace + " Here are the matching tasks in your list: ");
            String temp = "";
            for (Task tk : myList) {
                i++;
                temp += Ui.frontSpace + " " + i + ". " + tk + "\n";
//                System.out.println(Ui.frontSpace + " " + i + ". " + tk);
            }
            i = 0;
            return String.format(
                    Ui.frontSpace + " Here are the matching tasks in your list: \n" + temp);
        } else {
            throw new TaskNotExistException("task does not exist");
        }
    }
}