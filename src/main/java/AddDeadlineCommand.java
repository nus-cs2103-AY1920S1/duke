import java.util.Arrays;
import java.util.List;

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

    public void execute(List<Task> lst, Ui ui) throws DukeException {
        Deadline task = Deadline.createDeadline(tokens);
        lst.add(task);
        ui.printInput(task, lst);
    }



}
