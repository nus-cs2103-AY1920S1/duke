public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception{
        int index = Integer.parseInt(command.split(" ")[1]);
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task.toString());
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.println(String.format("Now you have %d tasks in the list", tasks.size()));
        }
        storage.updateFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
