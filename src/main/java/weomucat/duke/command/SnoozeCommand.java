package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.SnoozeTaskCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.IntervalParameter;
import weomucat.duke.command.parameter.ParameterOptions;

public class SnoozeCommand extends Command<SnoozeTaskCommandListener> {

  private static final String PARAMETER_BY = "/by";

  private IndexParameter taskIndex;
  private IntervalParameter duration;

  @Override
  public ParameterOptions getParameterOptions() {
    this.taskIndex = new IndexParameter("Task Index", true);
    this.duration = new IntervalParameter("Snooze Duration", true);
    return new ParameterOptions(this.taskIndex)
        .put(PARAMETER_BY, this.duration);
  }

  @Override
  Class<SnoozeTaskCommandListener> getListenerClass() {
    return SnoozeTaskCommandListener.class;
  }

  @Override
  DukeConsumer<SnoozeTaskCommandListener> getListenerConsumer() {
    return listener -> listener.snoozeTaskCommandUpdate(
        this.taskIndex.value(),
        this.duration.value());
  }
}
