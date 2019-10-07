public class FindCommand extends Command {
    String string;

    /**
     * Creates a FindCommand object to find tasks that contain a given string.
     * @param string The string to search for
     */
    FindCommand(String string) {
        this.string = string;
    }

    @Override
    String execute(TaskList tasks, Storage storage) {
        String result = "Here are the matching tasks in your list:";
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(string)) {
                result += "\n" + i + ". " + tasks.get(i);
            }
        }
        return result;
    }

    @Override
    boolean isExit() {
        return false;
    }
}
