package weomucat.doko.command;

import weomucat.doko.DokoConsumer;
import weomucat.doko.command.listener.ListTaskCommandListener;
import weomucat.doko.command.parameter.FlagParameter;
import weomucat.doko.command.parameter.ParameterOptions;

public class ListCommand extends Command<ListTaskCommandListener> {

  private static final String KEYWORD = "list";
  private static final String DESCRIPTION = "Lists tasks which are upcoming and not done.";

  private static final String PARAMETER_ALL = "/all";

  private static final String PARAMETER_ALL_DESCRIPTION =
      "If this flag is set, tasks which are overdue or done will be listed.";

  private FlagParameter all;

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
    this.all = new FlagParameter(PARAMETER_ALL_DESCRIPTION);
    return new ParameterOptions(null)
        .put(PARAMETER_ALL, this.all);
  }

  @Override
  Class<ListTaskCommandListener> getListenerClass() {
    return ListTaskCommandListener.class;
  }

  @Override
  DokoConsumer<ListTaskCommandListener> getListenerConsumer() {
    return listener -> listener.listTaskCommandUpdate(this.all.value());
  }
}
