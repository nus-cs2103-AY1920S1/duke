package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

public class DoneCommand extends Command<DoneTaskCommandListener> {

  private IndexParameter doneIndex;

  public DoneCommand(Collection<DoneTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.doneIndex = new IndexParameter("Done Index", true);
    return new ParameterOptions(this.doneIndex);
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.doneTaskCommandUpdate(this.doneIndex.value()));
  }
}
