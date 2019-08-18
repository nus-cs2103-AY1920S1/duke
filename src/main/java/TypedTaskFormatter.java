/* introduced in L4 */
class TypedTaskFormatter implements TaskFormatInterface {

    private final String name;
    private final boolean isDone;
    private final String sym;

    public TypedTaskFormatter(String name, boolean isDone, 
            String sym) {
        this.name = name;
        this.isDone = isDone;
        this.sym = sym; // short for symbol eg [T]
    }

    public String getStatusIcon() {
        //return tick or X symbols
        //return (this.isDone ? "\u2713" : "\u2718"); 
        return (this.isDone ? "V" : "X"); 
    }

    public String formatText() {
        return String.format("[%s][%s] %s", 
                this.sym, getStatusIcon(), this.name);
    }
}
