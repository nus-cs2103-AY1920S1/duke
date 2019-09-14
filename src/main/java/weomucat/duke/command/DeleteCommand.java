package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.ParameterOptions;

public class DeleteCommand extends Command<DeleteTaskCommandListener> {

  private IndexParameter deleteIndex;

  @Override
  public ParameterOptions getParameterOptions() {
    this.deleteIndex = new IndexParameter("Delete Index", true);
    return new ParameterOptions(this.deleteIndex);
  }

  @Override
  Class<DeleteTaskCommandListener> getListenersClass() {
    return DeleteTaskCommandListener.class;
  }

  @Override
  DukeConsumer<DeleteTaskCommandListener> getListenerConsumer() {
    return listener -> listener.deleteTaskCommandUpdate(this.deleteIndex.value());
  }
}
