import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * prints details for all available task when the list command is invoked.
     */
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            int count = i + 1;
            System.out.println(count + "." + task.toString());

        }

    }

    /**
     * returns details for all available task when the list command is invoked.
     */
    public String getTasksData(){
        String message = "";
        for (int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            int count = i + 1;
            message = message + task.toString() + "\r\n";

        }
        return message;
    }

    /**
     * marks a task as done and prints message that a task is marked as done.
     *
     * @param taskIndex position of a task in the taskList Arraylist
     *
     */

    public void markDone(int taskIndex){
        try{
            Task t = taskList.get(taskIndex);
            t.setCompleted();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("   [✓] " + t.getTask());

        } catch (IndexOutOfBoundsException ex){
            if (taskList.size() == 0){
                System.out.println("No tasks to be done.");
            } else {
                taskIndex += 1;
                System.out.println("Task " + taskIndex + " does not exist.");
            }
        }
    }

    /**
     * deletes a task from taskList Arraylist and prints message that a task is deleted.
     *
     * @param taskIndex position of a task in the taskList Arraylist
     *
     */
    public void deleteTask(int taskIndex){
        try{
            Task deleted_task = taskList.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + deleted_task.toString());

            if (taskList.size() <= 1){
                System.out.println("Now you have " + taskList.size() + " task in the list.");
            } else {
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
        } catch (IndexOutOfBoundsException ex){
            if (taskList.size() == 0) {
                System.out.println("Nothing to delete.");
            } else {
                taskIndex += 1;
                System.out.println("Task " + taskIndex + " does not exist.");
            }
        }
    }

    /**
     * creates and adds a deadline object into taskList Arraylist and prints a message related to the object's creation.
     *
     * @param input information in the form of: return book /by 2/12/2019 1800 (an example)
     *
     */
    public void addDeadLine(String input) {
        String[] words = input.split(" /by ");
        Deadline dl = new Deadline(words[0], words[1]);
        taskList.add(dl);
        dl.printAddedDeadline(taskList.size());

    }

    /**
     * creates and adds an event object and prints a message related to its creation.
     *
     * @param input information in the form of: proj-meeting /at 13/10/2019 6-8pm (an example)
     *
     */
    public void addEvent(String input){
        String[] words = input.split(" /at ");
        Events event = new Events(words[0], words[1]);
        taskList.add(event);
        event.printAddedEvent(taskList.size());
    }

    /**
     * creates and adds a todo object to taskList Arraylist and prints a message related to the object's creation.
     *
     * @param input information in the form of: proj-meeting (an example)
     *
     */
    public void addToDo(String input){
        Todo td = new Todo(input);
        taskList.add(td);
        td.printAddedTodo(taskList.size());

    }
    
}
