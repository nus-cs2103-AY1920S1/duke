public class Todo extends Task {

    private Todo() {}
    private Todo(String descr, boolean completed, int id) {
        super.description = descr;
        super.completed = completed;
        super.id = id;
        super.taskType = TaskType.T;
    }

    public static Todo create(String descr) throws EmptyDescriptionException {
        if (descr.equals(""))
            throw new EmptyDescriptionException("a todo");

        Task.totalNumOfTasks++;
        Todo newTask = new Todo(descr, false, Task.totalNumOfTasks);
        Task.taskList.add(newTask);
        return newTask;
    }
}
