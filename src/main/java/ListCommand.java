import java.util.ArrayList;
import java.util.List;

/**
 * ListCommand extends Command.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<String> sList = new ArrayList<>();
        String temp;

        int tasksSize = tasks.size();
        if (tasksSize == 0) {
            return Ui.frontSpace + " You have no tasks in your list.\n";
        } else {
            for (int i = 0; i < tasksSize; i++) {
                if ((i + 1) < 10) {
                    temp = Ui.frontSpace + " " +
                            (i + 1) + ". " + tasks.getTaskList().get(i) + "\n";
                } else {
                    temp = Ui.frontSpace + " " +
                            (i + 1) + "." + tasks.getTaskList().get(i) + "\n";
                }
                sList.add(temp);
            }
            String result = "";
            for(String str: sList){
                result += str;
            }
            return String.format(
                    Ui.frontSpace + " Here are the tasks in your list:\n" + "%s\n",
                    result);
        }
    }
}