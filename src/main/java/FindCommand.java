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
        ArrayList<Integer> indexList = new ArrayList<Integer>();

        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            String line = tasks.getTask(i).toString();
            String s = line.toLowerCase();
            if (s.contains(keyWord)) {
                matchList.add(line);
                indexList.add(i + 1);
            }
        }

        if (matchList.isEmpty()) {
            return "No matching tasks found :(\n";
        }

        String reply = "Here are the matching tasks in your list:\n";

        int i = 0;

        for (String line : matchList) {
            reply += (indexList.get(i)) + "." + line + "\n";
            i++;
        }

        return reply;
    }
}
