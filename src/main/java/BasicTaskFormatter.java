class BasicTaskFormatter implements TaskFormatInterface {

    private final String name;
    private final boolean isDone;

    public BasicTaskFormatter(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        //return tick or X symbols
        //return (this.isDone ? "\u2713" : "\u2718"); 
        return (this.isDone ? "V" : "X"); 
    }

    public String formatText() {
        return String.format("[%s] %s", 
                getStatusIcon(), this.name);
    }
}
