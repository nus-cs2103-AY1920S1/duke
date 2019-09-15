package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.ParameterOptions;

public class DoneCommand extends Command<DoneTaskCommandListener> {

  private IndexParameter doneIndex;

  @Override
  public ParameterOptions getParameterOptions() {
    this.doneIndex = new IndexParameter("Done Index", true);
    return new ParameterOptions(this.doneIndex);
  }

  @Override
  Class<DoneTaskCommandListener> getListenerClass() {
    return DoneTaskCommandListener.class;
  }

  @Override
  DukeConsumer<DoneTaskCommandListener> getListenerConsumer() {
    return listener -> listener.doneTaskCommandUpdate(this.doneIndex.value());
  }
}
