import java.util.ArrayList;

public class FindCommand extends Command{
    private String findKeywork;

    /**
     * Constructor for FindCommand
     * @param stringCommand String representation of the user input
     */
    public FindCommand(String stringCommand) {
        super(stringCommand);
        String[] stringSplit = stringCommand.split(" ");
        findKeywork = stringSplit[1];
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : taskList.getTasks()) {
            if (t.description.contains(findKeywork)) {
                tasks.add(t);
            }
        }
        ui.printFindMessage(tasks);
    }

    /**
     * Checks if Duke will end.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
