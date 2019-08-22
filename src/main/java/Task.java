public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static void doneTask(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The index of a done task cannot be empty.");
        }

        int numoftasks = Duke.taskList.size();
        int doneIndex = Integer.parseInt(words[1]);
        if (doneIndex > numoftasks || doneIndex <= 0) {
            throw new DukeException("☹ OOPS!!! The task is not found.");
        }

        Task currentTask = Duke.taskList.get(doneIndex - 1);
        currentTask.markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + currentTask);
        System.out.println("    ____________________________________________________________");
    }

    public static void deleteTask(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The index of a delete task cannot be empty.");
        }
        int numoftasks = Duke.taskList.size();
        int doneIndex = Integer.parseInt(words[1]);
        if (doneIndex > numoftasks || doneIndex <= 0) {
            throw new DukeException("☹ OOPS!!! The task is not found.");
        }
        
        Task currentTask = Duke.taskList.get(doneIndex - 1);
        Duke.taskList.remove(doneIndex - 1);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + currentTask);
        Duke.printNumber();
        System.out.println("    ____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
