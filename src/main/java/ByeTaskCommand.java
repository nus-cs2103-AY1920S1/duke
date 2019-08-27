public class ByeTaskCommand extends Command {
    ByeTaskCommand(){
        super(3);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        String taskListString = tasks.taskListString();
        ui.printString("Bye. Hope to see you again soon!");
        try {
            storage.write(taskListString);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
