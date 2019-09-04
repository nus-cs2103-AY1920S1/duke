import java.io.IOException;

public abstract class Command {

  public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

  public boolean isExit() {
    return this instanceof ExitCommand;
  }
}