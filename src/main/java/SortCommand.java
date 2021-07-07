import java.io.IOException;

public class SortCommand extends Command {

    String command;

    /**
     * Command constructor using string user input.
     *
     * @param command user String input
     */
    public SortCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Execute sort command. Sort command according to desc or task type.
     * @param task Tasklist data
     * @param ui Ui interfaces and strings
     * @param storage stored file
     * @return strings showing tasks are sorted.
     * @throws DukeException if any of raw values are invalid
     */
    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        String[] getSortingOrder = command.split(" ");
        String desc = "     Here are the tasks in your list:\n";

        if (getSortingOrder.length == 1) {
            throw new DukeException(" sort according to 1(desc) or 2(type)");
        }
        try {
            if (getSortingOrder[1].equals("1") || getSortingOrder[1].equals("desc")) {
                desc += "     Sorted according to desc of task.\n";
                task.sortByDescription();
            } else if (getSortingOrder[1].equals("2") || getSortingOrder[1].equals("type")) {
                desc += "     Sorted according to type of task.\n";
                task.sortByType();
            } else {
                String error = "     Input the correct command.\n     sort 1/2 only\n     or sort desc/type only\n";
                return error;
            }
            storage.writeToStorage(task);
            for (int i = 1; i <= task.getCount(); i++) {
                desc += "     " + i + "." + task.getTaskList().get(i - 1) + "\n";
            }
            return desc + "\n" + ui.showLine();
        } catch (IOException e) {
            return "WRITING ERROR";
        }
    }
}
