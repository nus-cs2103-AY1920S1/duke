package command;
import task.*;
import main.*;
public class AdminCommand implements Command {
    String commandType;
    int commandArg;
    public AdminCommand(String commandType, int commandArg) {
        this.commandType = commandType;
        this.commandArg = commandArg;
    }

    public AdminCommand(String commandType) {
        this.commandType = commandType;
        this.commandArg = -1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException, InvalidTaskException {
        if (commandType.equals("list")) {
            String result = "";
            for (int i = 0; i < tasks.size(); i = i + 1) {
                result = result + "    " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
            result = result.equals("") ? "\n" : result;
            String finalResult = "    ____________________________________________________________\n" +
                    "    Here are the tasks in your list:\n" +
                    result +
                    "    ____________________________________________________________";
            ui.nextLine(finalResult);
        } else if (commandType.equals("done")) {
            if (commandArg <=  0) {
                throw new InsufficientTaskArgumentException("Done requires a positive integer argument!");
            }
            Task targetedTask = tasks.get(commandArg - 1);
            Task.markAsDone(targetedTask);
            String result = "    ____________________________________________________________\n" +
                    "     Nice! I've marked this task as done: \n" +
                    "       " + targetedTask.toString() + "\n" +
                    "    ____________________________________________________________";
            ui.nextLine(result);
        } else {
            return ;
        }
    }
}
