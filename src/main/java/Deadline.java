public class Deadline extends Task{
    protected String  by;

    public Deadline(String description, String info) {
        super(description, info);
        super.type = Type.DEADLINE;
        String[] infos = info.split(" ", 2);
        by =  infos[1];
    }

    @Override
    public String toString() {

        return "[D][" + getStatusIcon() + "] " + description + " (by: " +  by + ")";
    }
}
