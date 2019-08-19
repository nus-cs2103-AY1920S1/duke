public class Deadline extends Task {

    private String death_data;

    public Deadline(String task_name, String death_data) {
        super(task_name);
        this.death_data = death_data;
    }

    @Override
    public String task_info() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715]";
        return "[D]" + indicator + get_name() + " by: " + death_data;
    }
}
