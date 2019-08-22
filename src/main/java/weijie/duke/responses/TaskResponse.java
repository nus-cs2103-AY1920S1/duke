package weijie.duke.responses;

import weijie.duke.models.Task;

import java.util.List;

public class TaskResponse {
    private String responseFormat;
    private List<Task> tasks;

    public TaskResponse(String responseFormat, List<Task> tasks) {
        this.responseFormat = responseFormat;
        this.tasks = tasks;
    }

    public String getFormattedResponse() {
        Object[] descriptions = tasks.stream()
                .map(Task::getDescription)
                .toArray();
        return String.format(responseFormat, descriptions);
    }
}
