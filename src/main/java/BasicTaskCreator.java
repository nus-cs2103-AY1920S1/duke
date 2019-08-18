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
        // removes "DeadLine" keyword
        String[] cmdList = command.split(" ");
        List<String> xs = 
            new LinkedList<String>(Arrays.asList(cmdList));
        xs.remove(0);

        // split remaining command delimited by "/by"
        String subcmd = listToString(xs);
        String[] subcmdList = subcmd.split(" /by ");

        String taskName = subcmdList[0];
        String date = subcmdList[1];

        return new DeadLinesImplementation(taskName, date,false);
    }

    public TaskInterface createTask(String command) {
        String[] cmdList = command.split(" ");

        if (cmdList[0].toUpperCase().equals("TODO")) {
            return createToDo(command);
        } else if 
            (cmdList[0].toUpperCase().equals("DEADLINE")) {
            return createDeadLine(command);
        } /*else if 
            (cmdList[0].toUpperCase().equals("EVENT")) {
            this.controller.doneTask(command);
        } else {
            this.controller.addTask(command);
        } */
        return new ToDosImplementation("ERROR",false);
    }

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

}
