package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;

public class ByeCommand extends Command<ByeCommandListener> {

  private static final String KEYWORD = "bye";
  private static final String DESCRIPTION = "Exits Duke.";

  @Override
  public String getKeyword() {
    return KEYWORD;
  }

  @Override
  String getDescription() {
    return DESCRIPTION;
  }

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
