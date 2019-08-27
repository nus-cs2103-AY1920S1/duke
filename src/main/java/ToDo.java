public class ToDo extends Task {


    public ToDo(String description) {
        super(description);
        this.identity = 'T';
    }

    public ToDo(int intDone, String description) {
        super(intDone, description);
        this.identity = 'T';
        if(intDone==1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toTextFile() {
        int done = isDone ? 1 : 0;
        return this.identity + " | " + done + " | " + this.description;
    }



}
