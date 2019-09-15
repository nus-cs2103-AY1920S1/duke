package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;

public class ByeCommand extends Command<ByeCommandListener> {

  @Override
  public ParameterOptions getParameterOptions() {
    return null;
  }

  @Override
  Class<ByeCommandListener> getListenerClass() {
    return ByeCommandListener.class;
  }

  @Override
  DukeConsumer<ByeCommandListener> getListenerConsumer() {
    return ByeCommandListener::byeCommandUpdate;
  }
}
