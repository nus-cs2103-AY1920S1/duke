public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskDone = tasks.getList().get(taskNumber);
        taskDone.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n  " + taskDone);
        storage.writeFile(tasks.getList());
    }

   public int getTaskNumber() {
        return taskNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            DoneCommand command = (DoneCommand) obj;
            return taskNumber == command.getTaskNumber();
        }

    }
}
