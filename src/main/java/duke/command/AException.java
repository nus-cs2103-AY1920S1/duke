package duke.command;

public class AException{

    /**
     * Prints out message when the description of ToDo is empty
     */
    public void emptyToDoException(){
        System.out.println("\tOOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Prints out message when the description of Deadline is empty
     */
    public void emptyDeadlineException(){
        System.out.println("\tOOPS!!! The description of a deadline cannot be empty.");
    }

    /**
     * Prints out message when the /by is not filled for Deadline
     */
    public void emptyByException(){
        System.out.println("\tOOPS!!! The date and time of deadline not specified.");
    }

    /**
     * Prints out message when the description of Event is empty
     */
    public void emptyEventException(){
        System.out.println("\tOOPS!!! The description of a event cannot be empty.");
    }

    /**
     * Prints out message when the /at is not filled for Event
     */
    public void emptyAtException(){
        System.out.println("\tOOPS!!! The date and time of event not specified.");
    }

    /**
     * Prints out message when the command is not understood
     */
    public void dontUnderstand(){
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints out message when the user of Duke wants to access (delete/done)
     * a task that is not in the list
     */
    public void exceedListSize(){
        System.out.println("\tOOPS!!! Task do not exist in list");
    }

    /**
     * Prints out message when the task has already been completed but the
     * user still issues the done command for that task
     */
    public void taskAlreadyCompleted(){
        System.out.println("\tOOPS!!! Task has already been completed!");
    }
}