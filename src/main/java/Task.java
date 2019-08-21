public class Task {
    protected String description;
    protected boolean isDone;
    protected Type type;
    protected String info;

    public Task(Type type, String description, String info) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.info = info;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getSymbol() {
        switch (type){
            case TODO:
                return "T";
            case DEADLINE:
                return "D";
            case EVENT:
                return "E";
            default:
                return "";
        }
    }

    public Type getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return  description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (info.equals("")) {
            return "[" + getSymbol() + "][" + getStatusIcon() + "] " + description;
        } else {
            String[] infos = info.split(" ", 2);
            return "[" + getSymbol() + "][" + getStatusIcon() + "] " + description + " (" + infos[0] +":  " +  infos[1] + ")";
        }
    }
}