package weomucat.doko.command;

import weomucat.doko.DokoConsumer;
import weomucat.doko.command.listener.DisplayCommandListener;
import weomucat.doko.ui.message.Message;

public class DisplayErrorCommand extends DisplayCommand {

  public DisplayErrorCommand(Message message) {
    super(message);
  }

  @Override
  DokoConsumer<DisplayCommandListener> getListenerConsumer() {
    return listener -> listener.displayError(this.message);
  }
}
