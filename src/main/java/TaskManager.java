import java.io.IOException;
import java.util.ArrayList;
public class TaskManager {
    private ArrayList<Task> todoList;

    public TaskManager(){
        this.todoList=new ArrayList<>();
    }

    protected Task addNewTask(String[] taskInfo) throws DukeException{
        if(taskInfo[0].equals("todo")){
            return this.addTodo(taskInfo[1]);
        }else if(taskInfo[0].equals("event")){
            return this.addEvent(taskInfo[1]);
        }else if (taskInfo[0].equals("deadline")){
            return this.addDeadline(taskInfo[1]);
        }
        throw new DukeException("Something wrong lol");
    }

    private Todo addTodo(String task) {
        task = task.trim();
        Todo todo = new Todo(task);
        this.todoList.add(todo);
        return todo;
    }

    private Event addEvent(String task) throws DukeException {
        int split = task.indexOf(" /at");
        if (split == -1) {
            throw new DukeException("Event time not given. Specify event time using \"/at\"");
        } else {
            try {
                String description = task.substring(0, split);
                String deadline = task.substring(split + 5);       //exception may occur
                description = description.trim();
                deadline = deadline.trim();
                Event event = new Event(description, deadline);
                this.todoList.add(event);
                return event;
            } catch (IndexOutOfBoundsException e) {         // happens when input is "event xx /at" with no time given
                throw new DukeException("No event time provided");
            }
        }
    }

    private Deadline addDeadline(String task) throws DukeException {
        int split = task.indexOf(" /by");
        if (split == -1) {
            throw new DukeException("Deadline not given. Specify deadline using \"/by\"");
        } else {
            try {
                String description = task.substring(0, split);
                String deadline = task.substring(split + 5);
                description = description.trim();
                deadline = deadline.trim();
                Deadline newDeadline = new Deadline(description, deadline);
                this.todoList.add(newDeadline);
                return newDeadline;
            } catch (IndexOutOfBoundsException e) { //same as event time
                throw new DukeException("Error: no deadline provided");
            }
        }
    }

    protected void load(Task task){
        this.todoList.add(task);
    }

    protected void savedDone(int index){
        this.todoList.get(index).setDone();
    }

    protected Task done(String doneIndex) throws DukeException{
        try {
            int doneInt = Integer.parseInt(doneIndex);
            this.todoList.get(doneInt - 1).setDone();
            return this.todoList.get(doneInt - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Bad task index"); // for wrong index provided
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task index");  //for index>array length
        }
    }

    protected Task delete(String deleteIndex) throws DukeException{  //exception same as done method
        try {
            int deleteInt = Integer.parseInt(deleteIndex);
            Task deletedTask = this.todoList.get(deleteInt - 1);
            this.todoList.remove(deleteInt - 1);
            return deletedTask;
        } catch (NumberFormatException e) {
            throw new DukeException("Bad task index");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task index");
        }
    }

    protected String generateList() {
        StringBuilder outputMessage = new StringBuilder();
        int i = todoList.size();
        for (int x = 1; x <= i; x++) {
            outputMessage.append("\n  " + x + "." + todoList.get(x - 1));
        }
        return outputMessage.toString();
    }

    protected int getSize(){
        return this.todoList.size();
    }
}
