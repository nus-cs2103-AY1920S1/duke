import java.io.IOException;

class TaskParser {

    static Task parse(String line) throws IOException {
        try {
            String[] taskComponents = line.split("-");
            switch (taskComponents[0]) {
            case "T":
                return new TodoTask(taskComponents[2],
                                    taskComponents[1].equals("1"));
            case "D":
                return new DeadlineTask(taskComponents[2],
                                        taskComponents[1].equals("1"),
                                        taskComponents[3]);
            case "E":
                return new EventTask(taskComponents[2],
                                     taskComponents[1].equals("1"),
                                     taskComponents[3]);
            default:
                throw new IOException("☹ OOPS!!! Saved file contains illegal formatting.");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IOException("☹ OOPS!!! Saved file contains illegal formatting.");
        }
    }

}
