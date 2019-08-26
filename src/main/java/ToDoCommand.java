public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Got it. I've added this task:");
        tasks.getTasks().add(new ToDo(this.description));
        System.out.println(tasks.getTasks().get(tasks.getTasks().size() - 1));
        System.out.println("Now you have " + tasks.getTasks().size()
                + " tasks in the list.");
    }
}
