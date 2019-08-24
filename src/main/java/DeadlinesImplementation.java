class DeadLinesImplementation implements TaskInterface,
    TypedTaskInterface {
    private final String name;
    private final String date;
    private final boolean isDone;
    private final TaskFormatInterface formatter;
    private final String sym = "D";

    public DeadLinesImplementation(String name, String date, 
            boolean isDone) {
        this.name = name;
        this.date = date;
        this.isDone = isDone;
        String outputText = String.format("%s (by: %s)", 
                name, date);
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

    public String getPrintDate() {
        if (this.date.length() > 0) {
            return "by: " + getDate();
        } else {
            return "";
        }
    }

    public String getDate() {
        if (this.date.length() > 0) {
            return this.date;
        } else {
            return "";
        }
    }

    // Override
    @Override
    public String toString() {
        return this.formatter.formatText();
    }

    public String getSaveFormat() {
        return this.formatter.getSaveFormat();
    }
          
}
