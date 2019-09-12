import java.io.IOException;
import java.util.Arrays;

public class AddDeadlineCommand extends Command {


    String [] tokens;

    public AddDeadlineCommand(String [] tokens) {
        this.tokens = tokens;
        this.commandType = CommandType.ADDDEADLINE;
    }

    public static AddDeadlineCommand addDeadlineIfValid(String [] tokens) {
        if (!Arrays.asList(tokens).contains("/by")) {
            throw new IllegalArgumentException("Missing deadline");
        } else {
            return new AddDeadlineCommand(tokens);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        Deadline task = Deadline.createDeadline(tokens);
        taskList.addToList(task);
        ui.printInput(task, taskList);
    }





}
