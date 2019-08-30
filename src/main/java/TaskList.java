import java.util.ArrayList;

public class TaskList{
   private Storage storage;
   private ArrayList<Task> todoList;

    public TaskList(ArrayList<Task> todoList){

        this.todoList = todoList;
    }

    public TaskList(){
        this.todoList = new ArrayList<>();
    }

   public void addTask(Task newTask){
        todoList.add(newTask);
   }


   public Task deleteTask(int index){

       return todoList.remove(index - 1);
   }



   public Task getTask(int taskNum){
        return this.todoList.get(taskNum - 1);
   }


   public int size(){
        return this.todoList.size();
    }

    public void markTaskDone(int taskNum){
        Task updatedTask = this.getTask(taskNum);
        updatedTask.markAsDone();
    }
}






    /*public void markTaskDone(String input) {
        //parse out the task number
        int taskNum = Integer.parseInt(input.substring(5)) - 1;

        //Ge the specific task object from toDoList
        Task updatedTask = toDoList.get(taskNum);

        //Mark the task as done in duke.txt
        try {
            storage.updateText(taskNum);
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
        updatedTask.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(updatedTask);
    }


    public void addTodoTask(String input) throws EmptyDescException {
        if (input.length() < 5) {
            throw new EmptyDescException("todo");
        } else {

            String desc = input.substring(5);

            storage.appendToFile("T", desc, null);

            Todo newTodo = new Todo(desc);

            toDoList.add(newTodo);

            int numTask = toDoList.size();


            System.out.println("Got it. I've added this task: \n" + "  "
                    + newTodo + "Now you have " +
                    numTask + " tasks in the list.");
        }
    }


    public void addDeadlineTask(String input) throws EmptyDescException {
        if (input.length() < 9) {
            throw new EmptyDescException("deadline");
        } else {


            int deadlineIndex = input.indexOf('/') + 4;
            String deadline = input.substring(deadlineIndex);
            String desc = input.substring(9, deadlineIndex - 5);


            storage.appendToFile("D", desc, deadline);

            Deadline newDeadline = new Deadline(desc, deadline);

            toDoList.add(newDeadline);

            int numTask = toDoList.size();


            System.out.println("Got it. I've added this task: \n" + "  "
                    + newDeadline + "Now you have " +
                    numTask + " tasks in the list.");
        }
    }

    public void addEventTask(String input) throws EmptyDescException {
        if (input.length() < 6) {
            throw new EmptyDescException("event");
        } else {

            int timeIndex = input.indexOf('/') + 4;
            String time = input.substring(timeIndex);

            String desc = input.substring(6, timeIndex - 5);

            storage.appendToFile("E", desc, time);


            Event newEvent = new Event(desc, time);

            toDoList.add(newEvent);

            int numTask = toDoList.size();


            System.out.println("Got it. I've added this task: \n" + "  "
                    + newEvent + "Now you have " +
                    numTask + " tasks in the list.");
        }
    }

    public void deleteTask(String input) {
        int taskNum = Integer.parseInt(input.substring(7)) - 1;
        //delete text from duke.txt
        try {
            storage.deleteText(taskNum);
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }

        Task removedTask = toDoList.remove(taskNum);
        System.out.println("Noted. I've removed this task:\n" + removedTask +
                "Now you have " + toDoList.size() + " tasks in the list.");

    } */

