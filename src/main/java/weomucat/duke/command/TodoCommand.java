package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;
import weomucat.duke.task.TodoTask;

public class TodoCommand extends Command<AddTaskCommandListener> {

  private static final String KEYWORD = "todo";
  private static final String DESCRIPTION =
      "Creates a todo task. A todo has no due date.";

  private static final String DEFAULT_PARAMETER_DESCRIPTION = "The description of this todo task.";

  private static final String DEFAULT_PARAMETER_NAME = "description";

  private StringParameter description;

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
    this.description = new StringParameter(DEFAULT_PARAMETER_DESCRIPTION, true,
        DEFAULT_PARAMETER_NAME);
    return new ParameterOptions(this.description);
  }

  @Override
  Class<AddTaskCommandListener> getListenerClass() {
    return AddTaskCommandListener.class;
  }

  @Override
  DukeConsumer<AddTaskCommandListener> getListenerConsumer() {
    return listener -> listener.addTaskCommandUpdate(new TodoTask(
        this.description.value()));
  }
}
