import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
/*
 * Look, if we need to have a list of commands to be more 
 * modular, we can 
 * 1. Wrapper class for BasicTaskCreator
 * 2. Facade to limit BasicTaskCreator
 * 3. Make a compositeTaskCreator and accept ToDoTaskCreator et al
 */
class BasicTaskCreator implements TaskCreator {
    public BasicTaskCreator() {
    }

    private TaskInterface createToDo(String command) 
        throws OWOException {

        String[] cmdList = command.split(" ");
        boolean isValid = checkCommand(command, "todo");
        List<String> xs = 
            new LinkedList<String>(Arrays.asList(cmdList));
        xs.remove(0);
        String taskName = listToString(xs);
        return new ToDosImplementation(taskName ,false);
    }

    private TaskInterface createDeadLine(String command)
        throws OWOException {

        String[] subcmdList = removeFirstAndDelimit(command, 
                " /by ");
        boolean isValid = checkCommand(command, "deadline");

        String taskName = subcmdList[0];
        String date = subcmdList.length > 1 ? subcmdList[1] : "";

        return new DeadLinesImplementation(taskName, date,false);
    }

    private TaskInterface createEvent(String command)
        throws OWOException {

        String[] subcmdList = removeFirstAndDelimit(command, 
                " /at ");
        boolean isValid = checkCommand(command, "event");

        String taskName = subcmdList[0];
        String date = subcmdList.length > 1 ? subcmdList[1] : "";

        return new EventsImplementation(taskName, date,false);
    }

    public TaskInterface createTask(String command) 
        throws OWOException {

        String[] cmdList = command.split(" ");

        if (cmdList[0].toUpperCase().equals("TODO")) {
            return createToDo(command);
        } else if 
            (cmdList[0].toUpperCase().equals("DEADLINE")) {
            return createDeadLine(command);
        } else if 
            (cmdList[0].toUpperCase().equals("EVENT")) {
            return createEvent(command);
        } else {
            throw new OWOException("OOPS >w<  UwU  UwU  "
                + "OwO is sowwy,\n"
                + "but OwO doesn't knyow what that means");
        } 
//        return new ToDosImplementation("ERROR",false);
    }

    public static boolean checkCommand(String cmd, String task) 
        throws OWOException {

        String[] cmdArr = cmd.split(" ");
        if (cmdArr.length < 2) {
            String errMsg = "OOPS UwU ^w^ " 
                + "(\u30fb\u0060\u03c9\u00b4\u30fb)\n"
                + "the descwiption of a " 
                + task 
                + " cannyot be empty!";
            throw new OWOException(errMsg);
        }            
        return true;
    }

    /* converts a str.split(" ") back into a string */
    public static String listToString(List<String> xs) {
        String output = "";
        boolean isFirst = true;
        for (String x : xs) {
            if (isFirst) {
                isFirst = false;
            } else {
                output += " ";
            }
            output += x;
        }
        return output;
    }

    public static String[] removeFirstAndDelimit(String command, 
            String delimit) {
        // removes "DeadLine" keyword
        String[] cmdList = command.split(" ");
        List<String> xs = 
            new LinkedList<String>(Arrays.asList(cmdList));
        xs.remove(0);

        // split remaining command delimited by "/at"
        String subcmd = listToString(xs);
        String[] subcmdList = subcmd.split(delimit);
        return subcmdList;
    }

}
