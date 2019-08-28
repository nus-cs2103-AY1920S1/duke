/**
 * This is the Parser class. It is used to response to the command given from the user.
 * @author Hua Lun
 */

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Parser {

    /**
     * <p>
     *     goodBye is used to exit the duke
     * </p>
     */
    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * <p>
     *     callList is used to print out the list of tasks
     * </p>
     * @param array the list of task
     */

    public void callList(ArrayList<TaskList> array) {
        TaskList.printList(array);
    }

    /**
     * <p>
     *     callDone is used to mark the task as completed
     * </p>
     * @param word get command
     * @param array the list of task
     */
    public void callDone(String word, ArrayList<TaskList> array) {
        String arr1[] = word.split(" ");
        int taskNum = Integer.parseInt(arr1[1]);
        TaskList.markAsDone(taskNum, array);
    }

    /**
     * <p>
     *     callTodo is used to handle to-do tasks
     * </p>
     * @param num task number
     * @param word get command
     * @param array the list of task
     */
    public void callTodo(int num, String word, ArrayList<TaskList> array) {
        TaskList todoT = new Todo(num, "[✗]", word, "todo");
        todoT.addList(todoT, array, num);
    }

    /**
     * <p>
     *    callEvent is used to handle event tasks
     * </p>
     * @param word get command
     * @param num task number
     * @param array the list of task
     */

    public void callEvent(String word, int num, ArrayList<TaskList> array) {
        String arr2[] = word.split("/at");
        DateTime dT1 = new DateTime(arr2[1]);
        TaskList eventT = new Event(num, "[✗]", arr2[0], "event", dT1);
        eventT.addList(eventT, array, num);
    }

    /**
     * <p>
     *     callDeadline is used to handle deadline tasks
     * </p>
     * @param word get command
     * @param num task number
     * @param array the list of task
     */
    public void callDeadline(String word, int num, ArrayList<TaskList> array) {
        String arr3[] = word.split("/by");
        String list[] = arr3[1].split(" ");
        DateTime dT2 = new DateTime(arr3[1]);
        TaskList deadlineT = new Deadline(num, "[✗]", arr3[0], "deadline", dT2);
        deadlineT.addList(deadlineT, array, num);
    }

    /**
     * <p>
     *     callDelete is used to handle delete tasks request
     * </p>
     * @param word get command
     * @param array the list of task
     */

    public void callDelete(String word, ArrayList<TaskList> array) {
        String arr4[] = word.split(" ");
        int taskNum = Integer.parseInt(arr4[1]);
        TaskList forDelete = array.get(taskNum - 1);
        forDelete.deleteTask(forDelete, array);
        int size = array.size();
        for(int i = 0; i < size; i++) {
            TaskList t = array.get(i);
            int current = t.getTaskNumber();
            String type = t.getType();
            if(current != i + 1) {
                if(type.equals("todo")) {
                    TaskList t1 = new Todo(i + 1, t.getTaskCheck(), t.getTaskName(), "todo");
                    array.set(i, t1);
                } else if(type.equals("event")) {
                    TaskList t2 = new Event(i + 1, t.getTaskCheck(), t.getTaskName(), "event", t.getAB());
                    array.set(i, t2);
                } else {
                    TaskList t3 = new Deadline(i + 1, t.getTaskCheck(), t.getTaskName(), "deadline", t.getAB());
                    array.set(i, t3);
                }
            }
        }
    }

    public void callFind(String word, ArrayList<TaskList> array) {
        ArrayList<TaskList> arr = new ArrayList<TaskList>();
        String arr5[] = word.split(" ");
        String searchWord = arr5[1];
        int size = array.size();

        for(int i = 0; i < size; i++) {
            TaskList t = array.get(i);
            String taskWord = t.getTaskName();
            String type = t.getType();
            if(taskWord.contains(searchWord)) {
                if(type.equals("todo")) {
                    System.out.println("here");
                    TaskList addTask = new Todo(i + 1, t.getTaskCheck(),
                            taskWord, type);
                    arr.add(addTask);
                } else if(type.equals("event")) {
                    TaskList addTask = new Event(i + 1, t.getTaskCheck(),
                            taskWord, type, t.getAB());
                    arr.add(addTask);
                } else if(type.equals("deadline")) {
                    TaskList addTask = new Deadline(i + 1, t.getTaskCheck(),
                            taskWord, type, t.getAB());
                    arr.add(addTask);
                }
            }
        }
        for(TaskList tL : arr) {
            System.out.println(tL);
        }
    }
}
