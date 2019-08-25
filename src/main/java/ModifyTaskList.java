import java.io.IOException;
import java.util.ArrayList;

public class ModifyTaskList {

    public static void modifyTaskList (ArrayList<Task> taskList, Task t, Duke.Action action) throws IOException {
        if (action == Duke.Action.ADD) {
            taskList.add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(taskList.get(taskList.size() - 1).toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            FileWriting.writeToFile(taskList);
        }
    }
    public static void modifyTaskList (ArrayList<Task> taskList, int taskNumber, Duke.Action action){
        if (action == Duke.Action.REMOVE) {
            try {
                System.out.println("Noted. I've removed this task:\n" + taskList.get(taskNumber).toString());
                taskList.remove(taskNumber);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
                FileWriting.writeToFile(taskList);
            }
            catch (IndexOutOfBoundsException | IOException err){
                System.out.println("You only have " + taskList.size() + " tasks, please choose a number from that\n");
            }
        }
        if (action == Duke.Action.DONE){
            try {
                taskList.get(taskNumber).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(taskNumber).toString() + "\n");
                FileWriting.writeToFile(taskList);
            }
            catch (IndexOutOfBoundsException | IOException err){
                System.out.println("You only have " + taskList.size() + " tasks, please choose a number from that\n");
            }
        }
    }
}
