import java.util.Date;

public class EventCommand extends Command {
    private String[] arguments;

    public EventCommand(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringDateConverter converter = new StringDateConverter();
            Date at = converter.convertStringToDate(arguments[1]);
            tasks.getTasks().add(new Event(arguments[0], at));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.getTasks().get(tasks.getTasks().size() - 1));
            System.out.println("Now you have " + tasks.getTasks().size()
                    + " tasks in the list.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
