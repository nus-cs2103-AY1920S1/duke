class ToDosImplementation implements TaskInterface, 
      TypedTaskInterface {

    private final String name;
    private final boolean isDone;
    private final TaskFormatInterface formatter;
    private final String sym = "T";

    public ToDosImplementation(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        //this.formatter = new BasicTaskFormatter(name, isDone);
        this.formatter = new TypedTaskFormatter(this);
    }

    public TaskInterface completeTask(){
        return new ToDosImplementation(this.name, true);
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getSymbol() {
        return this.sym;
    }

    public String getDate() {
        return "";
    }

    // Override
    @Override
    public String toString(){
        return this.formatter.formatText();
    }
          
}
