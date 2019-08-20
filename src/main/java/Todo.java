public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        //return super.taskNo + "." + "[" + super.doneSymbol + "] " + super.desc;
        return String.format("[%s][%s] %s", "T", super.getDoneSymbol(), this.desc);
    }

}
