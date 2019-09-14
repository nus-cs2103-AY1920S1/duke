import java.io.IOException;

/**
 * A command made from user input, that will determine the action to be taken on the task list.
 */
abstract class Command {

    abstract String execute(TaskList tasks, Storage storage) throws IOException, DukeException;

}
