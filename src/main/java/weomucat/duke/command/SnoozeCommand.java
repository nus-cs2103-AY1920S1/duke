package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.SnoozeTaskCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.IntervalParameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

public class SnoozeCommand extends Command<SnoozeTaskCommandListener> {

  private static final String PARAMETER_BY = "/by";

  private IndexParameter taskIndex;
  private IntervalParameter duration;

  public SnoozeCommand(Collection<SnoozeTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.taskIndex = new IndexParameter("Task Index", true);
    this.duration = new IntervalParameter("Snooze Duration", true);
    return new ParameterOptions(this.taskIndex)
        .put(PARAMETER_BY, this.duration);
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.snoozeTaskCommandUpdate(
        this.taskIndex.value(),
        this.duration.value()));
  }
}
