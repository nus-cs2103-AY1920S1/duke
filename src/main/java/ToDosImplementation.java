class ToDosImplementation implements TaskInterface {
    private final String name;
    private final boolean isDone;
    private final TaskFormatInterface formatter;

    public ToDosImplementation(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.formatter = new BasicTaskFormatter(name, isDone);
    }

    public TaskInterface completeTask(){
        return new ToDosImplementation(this.name, true);
    }

    // Override
    @Override
    public String toString(){
        return this.formatter.formatText();
    }
          
}
