package command;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;
import util.Storage;
import util.Ui;


public class AddCommand extends Command {

    private String command;

    public AddCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        String desc;
        if (command.startsWith(Instruction.TODO.toString())) {
            if (command.substring(4).isEmpty()) {
                Ui.emptyTaskMsg(Instruction.TODO.toString());
            } else {
                desc = command.substring(5);
                taskList.addTask(new ToDo(desc));
            }
        } else if (command.startsWith(Instruction.DEADLINE.toString())) {
            if (command.substring(8).isEmpty()) {
                Ui.emptyTaskMsg(Instruction.DEADLINE.toString());
            } else if (!command.contains("/by")) {
                Ui.missingDeadlineMsg();
            } else {
                int dateStartIndex = command.lastIndexOf("/by");
                desc = command.substring(9, dateStartIndex - 1);
                String ddl = command.substring(dateStartIndex + 4);
                taskList.addTask(new Deadline(desc, ddl));
            }
        } else if (command.startsWith(Instruction.EVENT.toString())) {
            if (command.substring(5).isEmpty()) {
                Ui.emptyTaskMsg(Instruction.EVENT.toString());
            } else if (!command.contains("/at")) {
                Ui.missingEventMsg();
            } else {
                int dateStartIndex = command.indexOf("/at");
                desc = command.substring(6, dateStartIndex - 1);
                String date = command.substring(dateStartIndex + 4);
                taskList.addTask(new Event(desc, date));
            }
        } else {
            Ui.unknownMsg();
        }


    }
}
