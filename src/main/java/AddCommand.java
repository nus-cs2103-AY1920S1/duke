public class AddCommand extends Command {
    private TaskType taskType;
    private String fullDescription;

    public AddCommand(TaskType taskType, String descr) {
        this.fullDescription = descr;
        this.taskType = taskType;
        super.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = TaskList.createTask(this.taskType, this.fullDescription); //create task will throw errors
        tasks.addData(newTask);

        ui.dukeRespond("Got it. I've added this task:",
                "  " + newTask.toString(),
                String.format("Now you have %d tasks in the list", tasks.getSize()));
        //write to file
        storage.updateFile(tasks);
    }
}
