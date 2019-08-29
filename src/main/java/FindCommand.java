import java.util.ArrayList;

public class FindCommand extends Command{
    String findKeywork;

    public FindCommand(String stringCommand) {
        super(stringCommand);
        String[] stringSplit = stringCommand.split(" ");
        findKeywork = stringSplit[1];
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
