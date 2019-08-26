public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index < 0 || this.index >= tasks.getTasks().size()) {
            Command incorrectCommand = new IncorrectCommand();
            incorrectCommand.execute(tasks, ui, storage);
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.getTasks().get(this.index));
            tasks.getTasks().remove(this.index);
            System.out.println("Now you have " + tasks.getTasks().size() + " in the list.");
        }
    }
}
