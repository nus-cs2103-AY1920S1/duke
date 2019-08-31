import java.text.ParseException;

public class AddDeadlineCommand extends AddCommand {
    private String input;
    private String[] details;

    public AddDeadlineCommand(String input) throws ArrayIndexOutOfBoundsException {
        super(input);
        details = Parser.getDetails(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ParseException {
        ui.showLine();
        taskList.add(new Deadline(details[0], Parser.getAsDate(details[1])));
        ui.out("Got it. I've added this task:");
        ui.out(taskList.get(taskList.size() - 1).toString());
        ui.out("Now you have " + taskList.size() + " tasks in the list.");
        ui.showLine();
    }
}
