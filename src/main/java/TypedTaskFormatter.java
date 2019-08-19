/* introduced in L4 */
class TypedTaskFormatter implements TaskFormatInterface {

    private final String name;
    private final boolean isDone;
    private final String sym;
    private final String date;

    public TypedTaskFormatter(TypedTaskInterface task) {
        this.name = task.getName();
        this.isDone = task.getIsDone();
        this.sym = task.getSymbol(); // short for symbol eg [T]
        this.date = task.getDate();
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (this.isDone ? "\u2713" : "\u2718"); 
        //return (this.isDone ? "V" : "X"); 
    }

    public boolean hasDate() {
        return this.date.length() > 0;
    }

    public String formatText() {
        if (hasDate()) {
            return String.format("[%s][%s] %s (%s)", 
                this.sym, getStatusIcon(), this.name, this.date);
        } else {
            return String.format("[%s][%s] %s", 
                this.sym, getStatusIcon(), this.name);

        }
    }
}
