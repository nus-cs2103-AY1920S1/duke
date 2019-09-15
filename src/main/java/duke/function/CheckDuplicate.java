package duke.function;

import duke.component.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Check for duplicate in the list.
 *
 * @author TeoShyanJie
 */
public class CheckDuplicate {

    private TaskList list;
    private Todo todo;
    private Deadline deadline;
    private Event event;

    /**
     * CheckDuplicate Constructor To-do task.
     * @param todo Todo Task.
     * @param list List of task.
     */
    public CheckDuplicate(Todo todo, TaskList list) {
        this.todo = todo;
        this.list = list;
    }

    /**
     * CheckDuplicate Constructor Deadline Task.
     * @param deadline Deadline Task.
     * @param list List of Task.
     */
    public CheckDuplicate(Deadline deadline, TaskList list) {
        this.deadline = deadline;
        this.list = list;
    }

    /**
     * CheckDuplicate Constructor Event Task.
     * @param event Event Task.
     * @param list List of Task.
     */
    public CheckDuplicate(Event event, TaskList list) {
        this.event = event;
        this.list = list;
    }

    /**
     * Check for duplicate To-do Task in list.
     * @return True/ False for duplicate Deadline Task.
     */
    public Boolean addTodo() {
        Boolean pass = true;

        for (Task item: list.getTask()) {
            if (item.getDescription().toLowerCase().contains(todo.getDescription().toLowerCase())) {
                pass = false;
                break;
            } else {
                pass = true;
            }
        }
        return pass;
    }

    /**
     * Check for duplicate Deadline Task in list.
     * @return True/ False for duplicate Deadline Task.
     */
    public Boolean addDeadline() {
        Boolean pass = true;

        for (Task item: list.getTask()) {
            if (item.getTime() == null) {
                continue;
            } else {
                if (item.getDescription().toLowerCase().contains(deadline.getDescription().toLowerCase())) {
                    String itemTime = item.getTime();
                    String taskTime = deadline.getBy();
                    if (itemTime.contains(taskTime)) {
                        pass = false;
                        break;
                    }
                } else {
                    pass = true;
                }
            }
        }
        return pass;
    }

    /**
     * Check for duplicate Event Task in list.
     * @return True/ False for duplicate Event Task.
     */
    public Boolean addEvent() {
        Boolean pass = true;

        for (Task item: list.getTask()) {
            if (item.getTime() == null) {
                continue;
            } else {
                if (item.getDescription().toLowerCase().contains(event.getDescription().toLowerCase())) {
                    String itemTime = item.getTime();
                    String taskTime = event.getAt();
                    if (itemTime.contains(taskTime)) {
                        pass = false;
                        break;
                    }
                } else {
                    pass = true;
                }
            }
        }
        return pass;
    }
}

