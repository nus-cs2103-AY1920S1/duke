import java.io.IOException;

public abstract class Command {

    public abstract StringBuilder execute(String input, TaskList taskList, Storage storage) throws DukeException, IOException;

}