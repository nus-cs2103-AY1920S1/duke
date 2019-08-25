package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
  private int index;

  public DoneCommand(int index) {
    this.index = index;
  }
  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    String current = tasks.get(index).storageString();
    tasks.get(index).setDone();
    String res = tasks.get(index).storageString();
    storage.replaceLine(current, res);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (obj instanceof DoneCommand) {
      DoneCommand other = (DoneCommand) obj;
      return other.index == this.index;
    }
    return false;
  }
}
