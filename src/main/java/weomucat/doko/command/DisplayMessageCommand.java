package weomucat.doko.command;

import weomucat.doko.DokoConsumer;
import weomucat.doko.command.listener.DisplayCommandListener;
import weomucat.doko.ui.message.Message;

public class DisplayMessageCommand extends DisplayCommand {

  public DisplayMessageCommand(Message message) {
    super(message);
  }

  @Override
  DokoConsumer<DisplayCommandListener> getListenerConsumer() {
    return listener -> listener.displayMessage(this.message);
  }
}
