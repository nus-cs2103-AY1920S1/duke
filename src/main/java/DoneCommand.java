public class DoneCommand extends Command {
    int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        Task t = allTasks.completeTask(this.taskNum);

        ui.printLine();
        ui.printSentence("Nice! I've marked this task as done: ");
        ui.printSentence("\t" + t);
        ui.printLine();

        super.execute(ui, storage, allTasks);
    }
}

