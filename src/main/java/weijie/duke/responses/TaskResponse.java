package weijie.duke.responses;

import weijie.duke.exceptions.DukeException;
import weijie.duke.models.Task;

import java.util.List;

public class TaskResponse {
    private String responseFormat;
    private List<Task> tasks;
    private DukeException exception;

    public TaskResponse(String responseFormat, List<Task> tasks) {
        this.responseFormat = responseFormat;
        this.tasks = tasks;
    }

    public TaskResponse(DukeException exception) {
        this.exception = exception;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the Task response formatted for human readability.
     * @return A String of the formatted response.
     */
    public String getFormattedResponse() {
        Object[] descriptions = tasks.stream()
                .map(Task::getDescription)
                .toArray();
        return String.format(responseFormat, descriptions);
    }

    public boolean isInvalidInput() {
        return exception != null;
    }

    public String getErrorMessage() {
        return exception.getMessage();
    }
}
