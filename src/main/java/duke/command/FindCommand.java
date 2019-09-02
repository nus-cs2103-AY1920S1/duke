package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;

public class FindCommand extends Command {

  private String keyword;

  public FindCommand(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    List<Task> foundTasks = tasks.getTasksWithKeywords(keyword);
    ui.printList(foundTasks);
    persistState(tasks, storage);
  }
}
