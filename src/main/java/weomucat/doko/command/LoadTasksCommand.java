package weomucat.doko.command;

import weomucat.doko.DokoConsumer;
import weomucat.doko.command.listener.LoadTasksCommandListener;
import weomucat.doko.command.parameter.ParameterOptions;
import weomucat.doko.ui.message.Message;

public class LoadTasksCommand extends Command<LoadTasksCommandListener> {

  public LoadTasksCommand() {
    setRunAfter(() -> new DisplayMessageCommand(
        new Message().addBody("Loaded Tasks!")));
  }

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
