public abstract class Command{

    /**
     * An abstract method that executes the command 
     *
     * @param taskList the taskList that the command requires
     * @return void
     */
    abstract public void execute(TaskList taskList);

}