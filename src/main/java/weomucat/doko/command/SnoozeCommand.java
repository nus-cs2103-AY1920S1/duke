package weomucat.doko.command;

import weomucat.doko.DokoConsumer;
import weomucat.doko.command.listener.SnoozeTaskCommandListener;
import weomucat.doko.command.parameter.IndexParameter;
import weomucat.doko.command.parameter.IntervalParameter;
import weomucat.doko.command.parameter.ParameterOptions;

public class SnoozeCommand extends Command<SnoozeTaskCommandListener> {

  private static final String KEYWORD = "snooze";
  private static final String DESCRIPTION =
      "Pushes back the due date of a deadline by a given duration.";

  private static final String PARAMETER_BY = "/by";

  private static final String DEFAULT_PARAMETER_DESCRIPTION = "The index of the task to snooze.";
  private static final String PARAMETER_BY_DESCRIPTION = "The duration to snooze the task.";

  private static final String DEFAULT_PARAMETER_NAME = "task";
  private static final String PARAMETER_BY_NAME = "duration";

  private IndexParameter taskIndex;
  private IntervalParameter duration;

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
    this.taskIndex = new IndexParameter(DEFAULT_PARAMETER_DESCRIPTION, true,
        DEFAULT_PARAMETER_NAME);
    this.duration = new IntervalParameter(PARAMETER_BY_DESCRIPTION, true,
        PARAMETER_BY_NAME);
    return new ParameterOptions(this.taskIndex)
        .put(PARAMETER_BY, this.duration);
  }

  @Override
  Class<SnoozeTaskCommandListener> getListenerClass() {
    return SnoozeTaskCommandListener.class;
  }

  @Override
  DokoConsumer<SnoozeTaskCommandListener> getListenerConsumer() {
    return listener -> listener.snoozeTaskCommandUpdate(
        this.taskIndex.value(),
        this.duration.value());
  }
}
