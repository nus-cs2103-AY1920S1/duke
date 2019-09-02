import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import json.ObjectHandler;
import json.Receiver;
import json.ValueHandler;

public enum TaskType {
	ToDo("T", Task::new),
	Deadline("D", DeadlineTask::new),
	Event("E", EventTask::new);


	private final String marker;
	private final Function<Map<String,Object>, ? extends Task> jsonConstructor;
	private TaskType(String marker,
			Function<Map<String,Object>, ? extends Task> jsonConstructor) {
		this.marker = marker;
		this.jsonConstructor = jsonConstructor;
	}

	public static TaskType fromString(String value) {
		for(TaskType t : TaskType.values()) {
			if(t.name().equalsIgnoreCase(value)) {
				return t;
			}
		}
		return TaskType.ToDo;
	}

	public String getMarker() {
		return marker;
	}

	public static class Builder implements ValueHandler<Task> {
		public ObjectHandler<Task> handleObject() {
			return ObjectHandler.DictValue.basicDict().map(dict -> {
				String typeString = dict.getOrDefault("type", "ToDo").toString();
				TaskType type = TaskType.fromString(typeString);
				return type.jsonConstructor.apply(dict);
			});
		}
	}
}
