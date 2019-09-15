package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.ParameterOptions;

public class DoneCommand extends Command<DoneTaskCommandListener> {

  private static final String KEYWORD = "done";
  private static final String DESCRIPTION = "Marks a task as done. Well done!";

  private static final String DEFAULT_PARAMETER_DESCRIPTION =
      "The index of the task to mark as done.";

  private static final String DEFAULT_PARAMETER_NAME = "task";

  private IndexParameter doneIndex;

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
    this.doneIndex = new IndexParameter(DEFAULT_PARAMETER_DESCRIPTION, true,
        DEFAULT_PARAMETER_NAME);
    return new ParameterOptions(this.doneIndex);
  }

  @Override
  Class<DoneTaskCommandListener> getListenerClass() {
    return DoneTaskCommandListener.class;
  }

  @Override
  DukeConsumer<DoneTaskCommandListener> getListenerConsumer() {
    return listener -> listener.doneTaskCommandUpdate(this.doneIndex.value());
  }
}
