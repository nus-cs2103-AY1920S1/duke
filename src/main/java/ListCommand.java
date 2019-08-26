public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < tasks.getTasks().size(); i = i + 1) {
            int number = i + 1;
            System.out.println(number + "." + tasks.getTasks().get(i));
        }
    }
}
