public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int id;
    static int total = 0;

    public Task(String description, int id){
        this.description = description;
        this.isDone = false;
        this.id = id;
        total++;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    public int getId(){
        return this.id;
    }

    public static int getTotal(){
        return total;
    }

    public void toggleDone(){
        this.isDone = true;
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
