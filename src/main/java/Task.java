public class Task {
    String name;
    boolean completed;
    String type;


    public Task(String taskInput) {
        name = taskInput;
        completed = false;
        type = null;
    }

    public String taskComplete() {
        completed = true;
        String firstLine = "Nice! I've marked this task as done:\n";
        String secondLine = "      " + this;

        return firstLine+secondLine;
    }

    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name;
        } else {
            return "[" + type + "]" + "[\u2718] " + name;
        }

    }


}
