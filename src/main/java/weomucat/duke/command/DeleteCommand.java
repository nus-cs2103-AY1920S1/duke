package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

public class DeleteCommand extends Command<DeleteTaskCommandListener> {

  private IndexParameter deleteIndex;

  public DeleteCommand(Collection<DeleteTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.deleteIndex = new IndexParameter("Delete Index", true);
    return new ParameterOptions(this.deleteIndex);
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.deleteTaskCommandUpdate(this.deleteIndex.value()));
  }
}
