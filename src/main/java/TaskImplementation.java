class TaskImplementation implements TaskInterface {
    private final String name;
    private final boolean isDone;
    private final TaskFormatInterface formatter;

    private TaskImplementation(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.formatter = new BasicTaskFormatter(name, isDone);
    }

    public static TaskInterface makeTask(String name) {
        return new TaskImplementation(name, false);
    }        

    public TaskInterface completeTask(){
        return new TaskImplementation(this.name, true);
    }

    // Override
    @Override
    public String toString(){
        return this.formatter.formatText();
    }
          
}
