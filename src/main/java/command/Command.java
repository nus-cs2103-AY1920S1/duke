package command;

import java.io.IOException;
import utils.TaskList;
import utils.Storage;
import utils.Ui;

public abstract class Command {

  public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

}