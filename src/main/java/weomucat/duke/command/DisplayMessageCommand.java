package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.DisplayCommandListener;
import weomucat.duke.ui.Message;

public class DisplayMessageCommand extends DisplayCommand {

  public DisplayMessageCommand(Message message) {
    super(message);
  }

  @Override
  DukeConsumer<DisplayCommandListener> getListenerConsumer() {
    return listener -> listener.displayMessage(this.message);
  }
}
