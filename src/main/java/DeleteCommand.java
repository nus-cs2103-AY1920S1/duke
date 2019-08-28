/**
 * DeleteCommand extends Command
 */
public class DeleteCommand extends Command {
    private String[] oneLine;
    String deleteMessage1;
    String deleteMessage2;

    public DeleteCommand(String[] oneLine) {
        this.oneLine = oneLine;

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int tasksSize = tasks.size();
        deleteMessage1 = " Noted. I've removed this task: ";
        deleteMessage2 = " Now you have " + (tasksSize - 1) + " tasks in the list.";
        int i = Integer.parseInt(oneLine[1].trim());
        if (i <= tasksSize && i > 0) {
            System.out.println(Ui.frontSpace + deleteMessage1);
            Task delete_task = tasks.getTaskList().get(i - 1);
            tasks.getTaskList().remove(i - 1);
            System.out.println(Ui.frontSpace + "   " + delete_task);
        } else {
            throw new TaskNotExistException("task does not exist");
        }
        try {
            storage.save(tasks);
        } catch (Exception e) {
            System.out.println(Ui.frontSpace + " duke.txt has problem");
        }

    }
}
