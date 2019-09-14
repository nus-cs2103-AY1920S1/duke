package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.ListTaskCommandListener;
import weomucat.duke.command.parameter.FlagParameter;
import weomucat.duke.command.parameter.ParameterOptions;

public class ListCommand extends Command<ListTaskCommandListener> {

  private static final String PARAMETER_ALL = "/all";

  private FlagParameter all;

  @Override
  public ParameterOptions getParameterOptions() {
    this.all = new FlagParameter();
    return new ParameterOptions(null)
        .put(PARAMETER_ALL, this.all);
  }

  @Override
  Class<ListTaskCommandListener> getListenersClass() {
    return ListTaskCommandListener.class;
  }

  @Override
  DukeConsumer<ListTaskCommandListener> getListenerConsumer() {
    return listener -> listener.listTaskCommandUpdate(this.all.value());
  }
}
