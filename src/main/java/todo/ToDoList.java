package todo;
import java.lang.StringBuilder;

public class ToDoList {
    private Task[] todoList;
    private int counter;

    public ToDoList() {
        this.todoList = new Task[100];
        this.counter = 0;
    }

    public String displayList() {
        StringBuilder output = new StringBuilder();
        int index;
        Task currentTask;

        output.append("    Here are the tasks in your list:\n");
        for (int i = 0 ; i < counter; i++) {
            currentTask = todoList[i];
            index = i + 1;
            output.append("    ");
            output.append(index);
            output.append(". ");
            output.append(currentTask);
            if (i < counter - 1) output.append('\n');
        }
        return output.toString();
    }

    public String addTask(String taskType, String taskDetail) {
        int numOfTasks = counter + 1;
        String[] inputTask = taskDetail.split("/");
        String description = inputTask[0];
        Task newTask = new Task("");

        switch (taskType) {
            case "todo":
                newTask = new Todo(description);
                break;
            case "deadline":
                String deadline = inputTask[1].substring(2);
                newTask = new Deadline(description, deadline);
                break;
            case "event":
                String date = inputTask[1].substring(2);
                newTask = new Event(description, date);
                break;
            default:
                break;
        }

        todoList[counter++] = newTask;
        return "    Got it. I've added this task:\n" +
                "      " + newTask +
                "\n    Now you have " + numOfTasks +
                " task" + (numOfTasks > 1 ? "s" : "") + " in the list.";
    }

    public String markTaskDone(int index) {
        todoList[index - 1].markAsDone();
        return "    Nice! I've marked this task as done:\n" +
               "        " + todoList[index - 1].getStatusIcon() +
                todoList[index - 1].getDescription();
    }
}
