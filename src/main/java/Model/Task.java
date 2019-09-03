package Model;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean isDone(){
        return this.isDone;
    }

    public char getIsDoneSymbol(){
        if(isDone){
            return '✓';
        } else {
            return 'x';
        }
    }

    public boolean markAsDone(){
        return this.isDone = true;
    }

    public boolean markAsUndone(){
        return this.isDone = false;
    }

    public char getSymbol(){
        return '?';
    }

    public String getDetails(){
        return null;
    }
}
