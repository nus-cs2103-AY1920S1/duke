import java.util.ArrayList;

public class FindCommand extends Command {
    protected String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord.toLowerCase();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Finds the keywords regardless of case sensitivity.
     * @param tasks TaskList containing the user's saved tasks
     * @param ui Ui object to handle the user input
     * @param storage storage object to determine where the executed results are stored
     * @return message response to user
     */
    @Override
    public String executeAndReturnMessage(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> matchList = new ArrayList<String>();
        int index = 0;

        for (Task task : tasks.tasks) {
            String line = task.toString().toLowerCase();
            if (line.contains(keyWord)) {
                matchList.add(line);
            }
        }

        if (matchList.isEmpty()) {
            return "No matching tasks found :(\n";
        }

        String reply = "Here are the matching tasks in your list:\n";

        for (String line : matchList) {
            reply += (index + 1) + "." + line + "\n";
            index++;
        }

        return reply;
    }
}
