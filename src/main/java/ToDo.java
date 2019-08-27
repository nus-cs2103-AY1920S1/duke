public class ToDo extends Task {
    //protected Boolean done;
    public ToDo(String command){
        super(command);
        this.done = false;
    }
    public String printer(){
        if(done){
            String result = "[T][✓] " + command;
            return result;
        }else{
            String result = "[T][✗] " + command;
            return result;
        }
    }

    public String printToOutput(){
        if(done){
            String result = "T | 1 | " + command;
            return result;
        }else{
            String result = "T | 0 | " + command;
            return result;
        }
    }

    public static Task outputAsToDo(String s){
        String[]segments = s.split("\\|");
        ToDo newTask = new ToDo(segments[2]);
        //System.out.println(segments[0] + "," + segments[1] + "," + segments[2]);
        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        }else{}
        return newTask;
    }

    public void taskDone(){
        done = true;
    }
}
