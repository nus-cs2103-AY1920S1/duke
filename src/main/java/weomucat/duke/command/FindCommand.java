package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.FindTaskCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;
import weomucat.duke.exception.DukeException;

public class FindCommand extends Command<FindTaskCommandListener> {

  private StringParameter keyword;

  public FindCommand(Collection<FindTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.keyword = new StringParameter("Find Keyword", true);
    return new ParameterOptions(this.keyword);
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.findTaskCommandUpdate(this.keyword.value()));
  }
}
