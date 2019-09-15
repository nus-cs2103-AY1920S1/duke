package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.LoadTasksCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.ui.Message;

public class LoadTasksCommand extends Command<LoadTasksCommandListener> {

  public LoadTasksCommand() {
    setRunAfter(() -> new DisplayMessageCommand(new Message("Loaded Tasks!")));
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
  DukeConsumer<LoadTasksCommandListener> getListenerConsumer() {
    return LoadTasksCommandListener::loadTasksCommandUpdate;
  }
}
