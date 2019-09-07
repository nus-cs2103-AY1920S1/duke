/**
 * This is the Ui class. It makes sense of what the input is.
 */

import java.util.ArrayList;
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
  
    public String run(String input, ArrayList<TaskList> array, int num) throws DukeExceptions{
        Parser p = new Parser(array, num);
        String output = "";
        if  (!input.equals("bye") && !input.equals("list") &&
                !input.contains("done") && !input.contains("todo") &&
                !input.contains("event") && !input.contains("deadline") &&
                !input.contains("delete") && !(input.contains("find"))) {

            assert !input.equals("bye") : "I do not know what this means.";
            assert !input.equals("list") : "I do not know what this means.";
            assert !input.contains("done") : "I do not know what this means";
            assert !input.contains("todo") : "I do not know what this means";
            assert !input.contains("event") : "I do not know what this means";
            assert !input.contains("deadline") : "I do not know what this means";
            assert !input.contains("delete") : "I do not know what this means";
            assert !input.contains("find") : "I do not know what this means";

            output = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

            throw new DukeExceptions("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (input.equals("todo") || input.equals("event") ||
                input.equals("deadline") || input.equals("find")) {

            assert input.equals("todo") : "Needs more information";
            assert input.equals("event") : "Needs more information";
            assert input.equals("deadline") : "Needs more information";
            assert input.equals("find") : "Needs more information";

            output = "☹ OOPS!!! The description of a " + input + " cannot be empty.";

            throw new DukeExceptions("☹ OOPS!!! The description of a " + input + " cannot be empty.");
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

