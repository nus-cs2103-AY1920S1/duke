package todo;
import java.lang.StringBuilder;

public class ToDo {
    private String[] todoList;
    private int counter;

    public ToDo() {
        this.todoList = new String[100];
        this.counter = 0;
    }

    public String displayList() {
        StringBuilder output = new StringBuilder();
        int index;
        for (int i = 0 ; i < counter; i++) {
            index = i + 1;
            output.append("    ");
            output.append(index);
            output.append(". ");
            output.append(todoList[i]);
            if (i < counter - 1) output.append('\n');
        }
        return output.toString();
    }

    public String addTask(String task) {
        todoList[counter++] = task;
        return "added: " + task;
    }
}
