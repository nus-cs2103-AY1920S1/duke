package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.LoadTasksCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;

public class LoadTasksCommand extends Command<LoadTasksCommandListener> {

  @Override
  public ParameterOptions getParameterOptions() {
    return null;
  }

  @Override
  Class<LoadTasksCommandListener> getListenersClass() {
    return LoadTasksCommandListener.class;
  }

  @Override
  DukeConsumer<LoadTasksCommandListener> getListenerConsumer() {
    return LoadTasksCommandListener::loadTasksCommandUpdate;
  }
}
