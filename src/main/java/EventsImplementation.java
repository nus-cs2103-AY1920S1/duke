class EventsImplementation implements TaskInterface {
    private final String name;
    private final String date;
    private final boolean isDone;
    private final TaskFormatInterface formatter;
    private final String sym = "E";

    public EventsImplementation(String name, String date, 
            boolean isDone) {
        this.name = name;
        this.date = date;
        this.isDone = isDone;
        String outputText = String.format("%s (at: %s)", 
                name, date);
        this.formatter = new TypedTaskFormatter(outputText, 
                isDone, sym);
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
