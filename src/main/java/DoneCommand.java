/**
 * DoneCommand extends Command.
 */
public class DoneCommand extends Command {
    private String[] oneLine;

    public DoneCommand(String[] oneLine) {
        this.oneLine = oneLine;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int i = Integer.parseInt(oneLine[1].trim());
        int tasksSize = tasks.size();

        if (i <= tasksSize && i > 0) {
//            System.out.println(Ui.frontSpace + " Nice! I've marked this task as done: \n");
            Task current = tasks.getTaskList().get(i - 1);
            current.markAsDone();
//            System.out.println(Ui.frontSpace + "   " + current);
            try {
                storage.save(tasks);
            } catch (Exception e) {
                System.out.println(Ui.frontSpace + " duke.txt has problem");
            }
            return String.format(
                    Ui.frontSpace + " Nice! I've marked this task as done: \n",
                    Ui.frontSpace + current + "\n");
        } else {
            throw new TaskNotExistException("task does not exist");
        }

    }
}