public class Command {
    private String COMMAND_MESSAGE;
    protected TaskList taskList;
    private int targetIndex;
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    public Command() {
    }
    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    private void setTargetIndex(int targetIndex) {
    }



}
