public class DeadlineCommand extends AddCommand {

    public DeadlineCommand(String description, String by) throws DukeException {
        super(new Deadline(description, by));
    }

}
