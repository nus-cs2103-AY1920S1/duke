package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.EventAtCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.ParameterOptions;

public class EventAtCommand extends Command<EventAtCommandListener> {

  private static final String PARAMETER_AT = "/at";

  private IndexParameter taskIndex;
  private IndexParameter scheduleIndex;

  @Override
  public ParameterOptions getParameterOptions() {
    this.taskIndex = new IndexParameter("Task Index", true);
    this.scheduleIndex = new IndexParameter("Schedule Index", true);
    return new ParameterOptions(this.taskIndex)
        .put(PARAMETER_AT, this.scheduleIndex);
  }

  @Override
  Class<EventAtCommandListener> getListenerClass() {
    return EventAtCommandListener.class;
  }

  @Override
  DukeConsumer<EventAtCommandListener> getListenerConsumer() {
    return listener -> listener.eventAtCommandUpdate(this.taskIndex.value(),
        this.scheduleIndex.value());
  }
}
