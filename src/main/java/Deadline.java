public class Deadline extends Task{
    protected String deadline;

    public Deadline(String sentence) throws DukeException{
        this(sentence.split("/by", 2)[0], (sentence.split("/by", 2).length > 1 ? sentence.split("/by", 2)[1] : "" ));
        this.taskType = "D";
    }

    public Deadline(String description, String deadline) throws DukeException{
        super(description);
        this.deadline = deadline;
        if(this.getTaskDescription().equals("")){
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if(this.getDeadline().equals("")){
            throw new DukeException("☹ OOPS!!! The date field of a deadline cannot be empty.");
        }
        this.taskType = "D";
    }

    public String getDeadline(){
        return this.deadline;
    }

    public Task markAsDone() throws DukeException{
        if(this.isDone){
            throw new DukeException("☹ OOPS!!! The deadline is already marked as done.");
        }
        Deadline completed = new Deadline(this.description, this.deadline);
        completed.isDone = true;
        return completed;
    }

    public String getTaskStatus(){
        return ("[" + this.getTaskType() + "] " + "[" + this.getStatusIcon() + "]" + this.getTaskDescription()
                + "(by:" + this.getDeadline() + ")" ); 
    }
}

