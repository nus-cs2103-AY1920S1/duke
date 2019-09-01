import java.util.ArrayList;

public class TaskList {
    /**
     * variable list contains all of user's tasks
     */
    private ArrayList<Task> list;

    /**
     * constructor if the hard drive already contains tasks
     * @param list is the input list from hard drive
     */
    public TaskList(ArrayList<Task> list){
        this.list = list;
    }

    /**
     * constructor in case the hard drive file is not read
     */
    public TaskList(){
        this.list = new ArrayList<>();
    }

    /**
     * prints the tasks on the list in the format of list required
     */
    public void printForList(){
        for (int i=1; i<=list.size(); i++) {
            System.out.println(i + ". " + list.get(i-1).printer());
        }
    }

    /**
     * outputs the tasks on the list in the format for hard drive output
     * @return the string which then will be outputted on the text file
     */
    public String printForOutput(){
        StringBuilder result= new StringBuilder();

        for (int i = 1; i <= list.size(); i++) {
            result.append(list.get(i - 1).printToOutput()).append("\n");
        }

        return result.toString();
    }

    /**
     * marks the specific task as done
     * @param x is the digit of the task to be marked as done
     */
    public void taskDone(int x){
        list.get(x).taskDone();
    }

    /**
     * returns the task details in a list-printable format
     * @param x is the digit of task to be printed
     * @return the task details
     */
    public String taskPrint(int x){
        return list.get(x).printer();
    }

    /**
     * to print latest task addition to the list
     * @return task details in string format
     */
    public String printLatest(){
        return list.get(list.size() - 1).printer();
    }

    /**
     * size of the Arraylist
     * @return int value of the size
     */
    public Integer size(){
        return list.size();
    }

    /**
     * remove task from list
     * @param x is the digit of the task in the list
     */
    public void remove(int x){
        list.remove(x);
    }

    /**
     * adds a new task to the list
     * @param newTask is the Task to add
     */
    public void add(Task newTask){
        list.add(newTask);
    }
}
