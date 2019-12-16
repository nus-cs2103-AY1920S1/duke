/* introduced in L4 */

/**
 * class which takes in a Task implementing relevant TypedTaskInterface
 * and has methods which returns the formatted string based on details of the Task
 * and type of format to save or to print.
 */
class TypedTaskFormatter implements TaskFormatInterface {

    private final TypedTaskInterface task;
    private final String name;
    private final boolean isDone;
    private final String sym;
    private final String date;
    private final String printDate;

    public TypedTaskFormatter(TypedTaskInterface task) {
        this.task = task;
        this.name = task.getName();
        this.isDone = task.getIsDone();
        this.sym = task.getSymbol(); // short for symbol eg [T]
        this.date = task.getDate();
        this.printDate = task.getPrintDate();
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (this.isDone ? "\u2713" : "\u2718"); 
        //return (this.isDone ? "V" : "X"); 
    }

    public int getStatusNumber() {
        return (this.isDone ? 1 : 0);
    }

    public boolean hasDate() {
        return this.date.length() > 0;
    }

    public String getSaveFormat() {
        if (hasDate()) {
            return String.format("%s | %d | %s | %s", 
                this.sym, getStatusNumber(), this.name, 
                this.date);
        } else {
            return String.format("%s | %d | %s", 
                this.sym, getStatusNumber(), this.name); 
        }
    }

    public String formatText() {
        if (hasDate()) {
            return String.format("[%s][%s] %s (%s)", 
                this.sym, getStatusIcon(), this.name, 
                this.printDate);
        } else {
            return String.format("[%s][%s] %s", 
                this.sym, getStatusIcon(), this.name);

        }
    }
}
