package command;


import main.Storage;
import main.TaskList;
import main.Ui;
import task.InsufficientTaskArgumentException;
import task.InvalidTaskException;
import java.util.ArrayList;

public class FindCommand implements Command {

    private String stringArgument;

    public FindCommand(String stringArgument) {
        this.stringArgument = stringArgument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException, InvalidTaskException {
        ArrayList<String> tasksThatMatch = tasks.findAllMatches(this.stringArgument);
        String matched = "";
        for (String s : tasksThatMatch) {
            matched = matched + s + "\n";
        }
        String result = "    ____________________________________________________________\n" +
                "    Here are the matching tasks in your list:\n" +
                matched +
                "    ____________________________________________________________";
        ui.nextLine(result);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Command: find, Argument:" + this.stringArgument;
    }
}
