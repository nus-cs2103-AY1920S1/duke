import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Command {

    protected boolean isExit = false;

    public Command() {
    }

    public abstract void execute(TaskList t, Ui ui, Storage storage) throws IOException;

    public abstract boolean isExit();
}
