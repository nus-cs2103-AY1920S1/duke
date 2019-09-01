public class ToDo extends Task {
    /**
     * constructor for the child class
     * @param command is the user input
     */
    public ToDo(String command){
        super(command);
        this.done = false;
    }

    /**
     * to print for "list" command
     * @return string in the format required
     */
    public String printer(){
        if(done){
            String result = "[T][✓] " + command;
            return result;
        }else{
            String result = "[T][✗] " + command;
            return result;
        }
    }

    /**
     * to print for text file
     * @return string in the format required
     */
    public String printToOutput(){
        if (done) {
            String result = "T | 1 | " + command;
            return result;
        } else {
            String result = "T | 0 | " + command;
            return result;
        }
    }

    /**
     * read user input as convert it into a Task
     * @param command is the user input string
     * @return a Task (ToDo) object
     */
    public static Task outputAsToDo(String command){
        String[]segments = command.split("\\|");
        ToDo newTask = new ToDo(segments[2]);

        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        } else {}

        return newTask;
    }

    /**
     * marks task as done
     */
    public void taskDone(){
        done = true;
    }
}
