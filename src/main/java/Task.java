public class Task {
    String name;
    boolean completed;

    public Task(String taskInput) {
        name = taskInput;
        completed = false;
    }

    public String getName() {
        return name;
    }

    //Change the "done" variable and return the String as completed
    public String taskComplete() {
        completed = true;
        String firstLine = "Nice! I've marked this task as done:\n";
        String secondLine = "      " + this;
        return firstLine+secondLine;
    }

    //Print with the "Done" variable
    @Override
    public String toString() {
        if(completed) {
            return  "[\u2713] " + name;
        } else {
            return  "[\u2718] " + name;
        }

    }


}