package weomucat.doko.command;

import weomucat.doko.DokoConsumer;
import weomucat.doko.command.listener.LoadTasksCommandListener;
import weomucat.doko.command.parameter.ParameterOptions;

public class LoadTasksCommand extends Command<LoadTasksCommandListener> {

  @Override
  public String getKeyword() {
    return null;
  }

  @Override
  String getDescription() {
    return null;
  }

  @Override
  public ParameterOptions getParameterOptions() {
    return null;
  }

  @Override
  Class<LoadTasksCommandListener> getListenerClass() {
    return LoadTasksCommandListener.class;
  }

  @Override
  DokoConsumer<LoadTasksCommandListener> getListenerConsumer() {
    return LoadTasksCommandListener::loadTasksCommandUpdate;
  }
}
