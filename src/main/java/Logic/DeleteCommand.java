package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class DeleteCommand implements Command {

    private String arguments;

    public DeleteCommand(String arguments){
        this.arguments = arguments;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        int index = Integer.parseInt(arguments);

        String content = "";
        content = content.concat("Noted. I've removed this task:\n");
        content = content.concat("[" + tasks.get(index - 1).getSymbol() + "][" + tasks.get(index - 1).getIsDoneSymbol() + "] " + tasks.get(index - 1).getDescription());
        if(tasks.get(index - 1).getSymbol() == 'D'){
            content = content.concat(" (by: " + tasks.get(index - 1).getDetails() + ")");
        } else if (tasks.get(index - 1).getSymbol() == 'E'){
            content = content.concat(" (at: " + tasks.get(index - 1).getDetails() + ")");
        }
        content = content.concat("\n");

        tasks.remove(index - 1);

        content = content.concat("You now have " + tasks.size() + " tasks in this list\n");
        ui.printData(content);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
