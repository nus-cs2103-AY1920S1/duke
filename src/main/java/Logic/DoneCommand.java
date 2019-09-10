package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class DoneCommand implements Command{
    private String arguments;

    public DoneCommand(String arguments){
        this.arguments = arguments;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        int index = Integer.parseInt(arguments);

        tasks.get(index - 1).markAsDone();

        String content = "Nice! I've marked this task as done:\n" +
                "[" + tasks.get(index - 1).getIsDoneSymbol() + "] " + tasks.get(index - 1).getDescription() +"\n";

        ui.printData(content);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
