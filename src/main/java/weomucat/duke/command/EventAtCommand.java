package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.EventAtCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.ParameterOptions;

public class EventAtCommand extends Command<EventAtCommandListener> {

  private static final String KEYWORD = "event_at";
  private static final String DESCRIPTION =
      "Confirms a schedule from a list of tentative schedules in an event.";

  private static final String PARAMETER_AT = "/at";

  private static final String DEFAULT_PARAMETER_DESCRIPTION = "The index of the event.";
  private static final String PARAMETER_AT_DESCRIPTION =
      "The index of the tentative schedule within the event to choose.";

  private static final String DEFAULT_PARAMETER_NAME = "task";
  private static final String PARAMETER_AT_NAME = "schedule";

  private IndexParameter taskIndex;
  private IndexParameter scheduleIndex;

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
    this.scheduleIndex = new IndexParameter(PARAMETER_AT_DESCRIPTION, true,
        PARAMETER_AT_NAME);
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
