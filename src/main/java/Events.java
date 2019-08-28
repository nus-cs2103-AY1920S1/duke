public class Events extends Task{

    protected String at;

    public Events(String name,boolean completionStatus,String at) {
        super(name , completionStatus);
        this.at = at;
    }
    @Override
    public String getOverallStatus() {
        return "[E]" + getCurrentStatus() + name + "(at:" + at + ")";
    }
}
