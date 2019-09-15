package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.DisplayCommandListener;
import weomucat.duke.ui.Message;

public class DisplayErrorCommand extends DisplayCommand {

  public DisplayErrorCommand(Message message) {
    super(message);
  }

  @Override
  DukeConsumer<DisplayCommandListener> getListenerConsumer() {
    return listener -> listener.displayError(this.message);
  }
}
