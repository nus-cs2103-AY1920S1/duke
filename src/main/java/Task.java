public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected char identity;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(int intDone, String description) {
        this.description = description;
        if(intDone==1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = !isDone;
    }

    @Override
    public String toString(){
        return getStatusIcon() + " " + this.description;
    }

    /**
     * abstract method.
     * @return
     */
    public abstract String toTextFile() ;

}
