package todo;
import java.lang.StringBuilder;

public class ToDo {
    private Task[] todoList;
    private int counter;

    public ToDo() {
        this.todoList = new Task[100];
        this.counter = 0;
    }

    public String displayList() {
        StringBuilder output = new StringBuilder();
        int index;
        Task currentTask;

        for (int i = 0 ; i < counter; i++) {
            currentTask = todoList[i];
            index = i + 1;
            output.append("    ");
            output.append(index);
            output.append(". ");
            output.append(currentTask.getStatusIcon());
            output.append(currentTask.getDescription());
            if (i < counter - 1) output.append('\n');
        }
        return output.toString();
    }

    public String addTask(String task) {
        todoList[counter++] = new Task(task);
        return "added: " + task;
    }

    public String markTaskDone(int index) {
        todoList[index - 1].markAsDone();
        return "    Nice! I've marked this task as done:\n" +
               "        " + todoList[index - 1].getStatusIcon() +
                todoList[index - 1].getDescription();
    }
}
