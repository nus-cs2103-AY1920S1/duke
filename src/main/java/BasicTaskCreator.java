import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
class BasicTaskCreator implements TaskCreator {
    public BasicTaskCreator() {
    }

    private TaskInterface createToDo(String command) {
        String[] cmdList = command.split(" ");
        List<String> xs = 
            new LinkedList<String>(Arrays.asList(cmdList));
        xs.remove(0);
        String taskName = listToString(xs);
        return new ToDosImplementation(taskName ,false);
    }

    private TaskInterface createDeadLine(String command) {
        String[] subcmdList = removeFirstAndDelimit(command, 
                " /by ");

        String taskName = subcmdList[0];
        String date = subcmdList[1];

        return new DeadLinesImplementation(taskName, date,false);
    }

    private TaskInterface createEvent(String command) {
        String[] subcmdList = removeFirstAndDelimit(command, 
                " /at ");

        String taskName = subcmdList[0];
        String date = subcmdList[1];

        return new EventsImplementation(taskName, date,false);
    }

    public TaskInterface createTask(String command) {
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
            
        } 
        return new ToDosImplementation("ERROR",false);
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
