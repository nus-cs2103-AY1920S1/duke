package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.EventAtCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

public class EventAtCommand extends Command<EventAtCommandListener> {

  private static final String PARAMETER_AT = "/at";

  private IndexParameter taskIndex;
  private IndexParameter scheduleIndex;

  public EventAtCommand(Collection<EventAtCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.taskIndex = new IndexParameter("Task Index", true);
    this.scheduleIndex = new IndexParameter("Schedule Index", true);
    return new ParameterOptions(this.taskIndex)
        .put(PARAMETER_AT, this.scheduleIndex);
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.eventAtCommandUpdate(this.taskIndex.value(),
        this.scheduleIndex.value()));
  }
}
