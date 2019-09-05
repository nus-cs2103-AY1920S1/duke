import java.util.ArrayList;

public class FindCommand extends Command {
    protected String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> matchList = new ArrayList<String>();
        int index = 0;

        for (Task task : tasks.tasks) {
            String line = task.toString();
            if (line.contains(keyWord)) {
                matchList.add(line);
            }
        }

        String reply = "Here are the matching tasks in your list:\n";

        for (String line : matchList) {
            reply += (index + 1) + "." + line + "\n";
            index++;
        }

        return reply;
    }
}
