package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.ListTaskCommandListener;
import weomucat.duke.command.parameter.FlagParameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

public class ListCommand extends Command<ListTaskCommandListener> {

  private static final String PARAMETER_ALL = "/all";

  private FlagParameter all;

  public ListCommand(Collection<ListTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.all = new FlagParameter();
    return new ParameterOptions(null)
        .put(PARAMETER_ALL, this.all);
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.listTaskCommandUpdate(this.all.value()));
  }
}
