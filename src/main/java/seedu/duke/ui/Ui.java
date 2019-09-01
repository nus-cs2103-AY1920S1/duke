package seedu.duke.ui;

import seedu.duke.DukeException;
import seedu.duke.task.Todo;
import seedu.duke.task.Event;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.tasklist.TaskList;

public class Ui {
    private final String underscore = "    ____________________________________________________________" + "\n" ;

    public Ui(){

    }

    public void showLoadingError(DukeException e){
        System.out.println(e.getMessage());
    }

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String underscore = "    ____________________________________________________________" + "\n" ;
        String intro = underscore +
                "      Hello! I'm seedu.duke.Duke " + "\n" +
                "      What can I do for you?" + "\n" +
                underscore ;
        System.out.println(logo + intro);
    }

    public void printList(TaskList tasks){
        String output = underscore + "     Here are the tasks in your list:\n";

        output += getTasksInList(tasks);

        System.out.println(output);
    }

    public void printDoneSequence(TaskList tasks, int taskNum){
        String output = underscore + "     Nice! I've marked this task as done:\n" +
                "       [" + tasks.getTask(taskNum).getStatusIcon() + "] " + tasks.getTask(taskNum).getTaskName() +
                "\n" + underscore;
        System.out.println(output);
    }

    public void printTodoSequence(TaskList tasks, Todo newTodo){
        String output = underscore + "     Got it. I've added this task:\n       "
                + newTodo.toString() + getTasksRemainingSequence(tasks.getSize());
        System.out.println(output);
    }

    public void printDeadlineSequence(TaskList tasks, Deadline newDeadline){
        String output = underscore + "     Got it. I've added this task:\n       "
                + newDeadline.toString() + getTasksRemainingSequence(tasks.getSize());
        System.out.println(output);
    }

    public void printEventSequence(TaskList tasks, Event newEvent ){
        String output = underscore + "     Got it. I've added this task:\n       "
                + newEvent.toString() + getTasksRemainingSequence(tasks.getSize());
        System.out.println(output);
    }

    public void printDeleteSequence(TaskList tasks, Task taskToDelete){
        String output = underscore + "     Noted. I've removed this task.\n       " +
                taskToDelete.toString() + getTasksRemainingSequence(tasks.getSize());
        System.out.println(output);
    }

    public void printByeSequence(){
        String output = underscore + "\n" + "     " + "Bye. Hope to see you again soon!" + "\n" + underscore + "\n";
        System.out.print(output);
    }

    public String getTasksRemainingSequence(int numOFTaskRemaining){
        String output = "\n     Now you have " +
                numOFTaskRemaining + " tasks in the list.\n" + underscore;
        return output;
    }

    /**
     * Returns the string containing tasks in a pre-formatted form.
     * Eg. "1.[E][✘] Run (at: ERC)
     *      2.[D][✓] IPPT (by: 21st of December 2004, 8.15am)
     *      3.[E][✓] Lecture (at: LT7A)".
     *
     * @param tasks TaskList object
     * @return String of tasks
     */
    public String getTasksInList(TaskList tasks){
        String output = "";
        for (int i = 0; i < tasks.getSize(); i++){
            output += "     " + (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        output += underscore;
        return (output);
    }

    public void printFoundTasks(TaskList tasks){
        String output = underscore + "     Here are the matching tasks in your list:\n";

        output += getTasksInList(tasks);

        System.out.println(output);
    }



}
