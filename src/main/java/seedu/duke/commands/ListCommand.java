package seedu.duke.commands;

public class ListCommand extends Command {

    @Override
    protected void execute() {
        String reply = "Here are the tasks in your list:\n\t ";
        for (int i = 0; i < taskList.size(); i++) {
            reply += (i + 1) + "." + taskList.get(i);
            if (i != taskList.size() - 1) {
                reply += "\n\t ";
            }
        }
        ui.printReply(reply);
    }
}
