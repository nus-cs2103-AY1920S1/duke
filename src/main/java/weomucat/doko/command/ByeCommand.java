package weomucat.doko.command;

import weomucat.doko.DokoConsumer;
import weomucat.doko.command.listener.ByeCommandListener;
import weomucat.doko.command.parameter.ParameterOptions;

public class ByeCommand extends Command<ByeCommandListener> {

  private static final String KEYWORD = "bye";
  private static final String DESCRIPTION = "Exits Doko.";

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
  DokoConsumer<ByeCommandListener> getListenerConsumer() {
    return ByeCommandListener::byeCommandUpdate;
  }
}
