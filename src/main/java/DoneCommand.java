public class DoneCommand extends Command {

    public DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        int index = Integer.valueOf(command.split(" ")[1]);
        tasks.getList().get(index - 1).complete();
        storage.updateFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
