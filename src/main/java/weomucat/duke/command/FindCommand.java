package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.FindTaskCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;

public class FindCommand extends Command<FindTaskCommandListener> {

  private StringParameter keyword;

  @Override
  public ParameterOptions getParameterOptions() {
    this.keyword = new StringParameter("Find Keyword", true);
    return new ParameterOptions(this.keyword);
  }

  @Override
  Class<FindTaskCommandListener> getListenersClass() {
    return FindTaskCommandListener.class;
  }

  @Override
  DukeConsumer<FindTaskCommandListener> getListenerConsumer() {
    return listener -> listener.findTaskCommandUpdate(this.keyword.value());
  }
}
