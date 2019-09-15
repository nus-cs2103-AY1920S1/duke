package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.FindTaskCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;

public class FindCommand extends Command<FindTaskCommandListener> {

  private static final String KEYWORD = "find";
  private static final String DESCRIPTION =
      "Searches for all tasks whose description matches a keyword.";

  private static final String DEFAULT_PARAMETER_DESCRIPTION =
      "The needle for any partially matching task description.";

  private static final String DEFAULT_PARAMETER_NAME = "keyword";

  private StringParameter keyword;

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
    this.keyword = new StringParameter(DEFAULT_PARAMETER_DESCRIPTION, true,
        DEFAULT_PARAMETER_NAME);
    return new ParameterOptions(this.keyword);
  }

  @Override
  Class<FindTaskCommandListener> getListenerClass() {
    return FindTaskCommandListener.class;
  }

  @Override
  DukeConsumer<FindTaskCommandListener> getListenerConsumer() {
    return listener -> listener.findTaskCommandUpdate(this.keyword.value());
  }
}
