package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.command.parameter.IndexParameter;
import weomucat.duke.command.parameter.ParameterOptions;

public class DeleteCommand extends Command<DeleteTaskCommandListener> {

  private static final String KEYWORD = "delete";
  private static final String DESCRIPTION = "Deletes a task forever.";

  private static final String DEFAULT_PARAMETER_DESCRIPTION = "The index of the task to delete.";

  private static final String DEFAULT_PARAMETER_NAME = "task";

  private IndexParameter deleteIndex;

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
    this.deleteIndex = new IndexParameter(DEFAULT_PARAMETER_DESCRIPTION, true,
        DEFAULT_PARAMETER_NAME);
    return new ParameterOptions(this.deleteIndex);
  }

  @Override
  Class<DeleteTaskCommandListener> getListenerClass() {
    return DeleteTaskCommandListener.class;
  }

  @Override
  DukeConsumer<DeleteTaskCommandListener> getListenerConsumer() {
    return listener -> listener.deleteTaskCommandUpdate(this.deleteIndex.value());
  }
}
