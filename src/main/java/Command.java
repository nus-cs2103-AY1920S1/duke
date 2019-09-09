import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Command {
    public Command() {

    }

    public abstract void execute(TaskList tl, Storage st) throws IOException;
}
