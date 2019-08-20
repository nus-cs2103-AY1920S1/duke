public class Todo extends Task {

    private Todo() {
        super.description = "";
        super.completed = false;
        super.id = 0;
        super.taskType = "";
    }
    private Todo(String descr, boolean completed, int id) {
        super.description = descr;
        super.completed = completed;
        super.id = id;
        super.taskType = "T";
    }

    public static Todo create(String descr) {
        //assume legit; not using optional for now
        Task.totalNumOfTasks++;
        Todo newTask = new Todo(descr, false, Task.totalNumOfTasks);
        Task.taskList.add(newTask);
        return newTask;
    }
}
