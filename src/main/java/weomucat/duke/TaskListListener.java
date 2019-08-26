package weomucat.duke;

public interface TaskListListener<T> {
	void update(T t) throws DukeException;
}
