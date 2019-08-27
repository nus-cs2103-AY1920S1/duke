public class ToDoCommand extends Command {
    ToDo td;

    public ToDoCommand(ToDo td) {
        this.td = td;
    }

    public void execute(TaskList tasks, UI ui) {
        String taskMessage = tasks.addToDo(td);
        ui.showAddedMessage(taskMessage, tasks.getTasksSize());
    }

    public boolean isExit() {
        return false;
    }

}