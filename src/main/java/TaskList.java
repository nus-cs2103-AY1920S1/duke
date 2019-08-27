import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    Task deleteTask(int taskId) throws DukeException {
        try {
            return this.tasks.remove(taskId - 1);
        } catch (Exception e) {
            throw new DukeException("Failed to find task.");
        }
    }

    Task markDone(int taskId) throws DukeException {
        try {
            Task task = this.tasks.get(taskId - 1);
            task.markAsDone();
            return task;
        } catch (Exception e) {
            throw new DukeException("Failed to find task.");
        }
    }

    boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Generates a numbered list of tasks matching a query
     * @param query Query string
     * @return Numbered list of matching tasks
     */
    String query(String query) {
        TaskList match = new TaskList();

        for (Task t : this.tasks) {
            if (t.getDescription().contains(query)) {
                match.addTask(t);
            }
        }

        return match.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(String.format("%d.%s%n", i + 1, tasks.get(i).toString()));
        }
        return output.toString().trim();
    }
}
