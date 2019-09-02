/**
 * This is the Ui class. It makes sense of what the input is.
 */

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Ui {

    static ArrayList<TaskList> array;
    static int num;

    public Ui(ArrayList<TaskList> arr, int n) {
        array = arr;
        num = n;
    }

    /**
     * <p>
     *     run is used to start up duke.
     * </p>
     * @param input command
     * @param array the list of task
     * @param num start with 1 for first task
     */
  
    public String run(String input, ArrayList<TaskList> array, int num) {
        Parser p = new Parser(array, num);
        String output = "";
        if  (!input.equals("bye") && !input.equals("list") && !
                input.contains("done") && !input.contains("todo") &&
                !input.contains("event") && !input.contains("deadline") &&
                !input.contains("delete") && !(input.contains("find"))) {
            //System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            output = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else if (input.equals("todo") || input.equals("event") ||
                input.equals("deadline") || input.equals("find")) {
            //System.out.println("☹ OOPS!!! The description of a t//odo cannot be empty.");
            output = "☹ OOPS!!! The description of a " + input + " cannot be empty.";
        } else if (input.equals("bye")) {
            return p.goodBye();
        } else if (input.equals("list")) {
            return p.callList(array);
        } else if (input.contains("done")) {
            return p.callDone(input, array);
        } else if (input.contains("todo")) {
            return p.callTodo(num, input, array);
        } else if (input.contains(("event"))) {
            String outputEvent = p.callEvent(input, num, array);
            return outputEvent;
        } else if (input.contains("deadline")) {
            String outputDeadline = p.callDeadline(input, num, array);
            return outputDeadline;
        } else if (input.contains("delete")) {
            return p.callDelete(input, array);
        } else if (input.contains("find")) {
            return p.callFind(input, array);
        }
        return output;
    }

}

