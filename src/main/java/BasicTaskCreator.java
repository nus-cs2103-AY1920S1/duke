import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
class BasicTaskCreator implements TaskCreator {
    public BasicTaskCreator() {
    }

    public TaskInterface createTask(String command) {
        String[] cmdList = command.split(" ");

        if (cmdList[0].toUpperCase().equals("TODO")) {
            List<String> xs = 
                new LinkedList<String>(Arrays.asList(cmdList));
            xs.remove(0);
            String taskName = listToString(xs);
            return new ToDosImplementation(taskName ,false);
        } /* else if 
            (cmdList[0].toUpperCase().equals("DEADLINE")) {
            this.controller.doneTask(command);
        } else if 
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
