package weomucat.duke.command;

import weomucat.duke.command.listener.DisplayCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.ui.Message;

public abstract class DisplayCommand extends Command<DisplayCommandListener> {

  Message message;

  public DisplayCommand(Message message) {
    this.message = message;
  }

  @Override
  Class<DisplayCommandListener> getListenerClass() {
    return DisplayCommandListener.class;
  }

  @Override
  public ParameterOptions getParameterOptions() {
    return null;
  }
}
