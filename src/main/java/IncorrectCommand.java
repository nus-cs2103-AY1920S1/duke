public class IncorrectCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("You have entered an invalid command");
    }
}
