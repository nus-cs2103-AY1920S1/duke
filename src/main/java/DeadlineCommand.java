import java.util.Date;

public class DeadlineCommand extends Command {
    private String[] arguments;

    public DeadlineCommand(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringDateConverter converter = new StringDateConverter();
            Date by = converter.convertStringToDate(arguments[1].trim());
            tasks.getTasks().add(new Deadline(arguments[0], by));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.getTasks().get(tasks.getTasks().size() - 1));
            System.out.println("Now you have " + tasks.getTasks().size()
                    + " tasks in the list.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
