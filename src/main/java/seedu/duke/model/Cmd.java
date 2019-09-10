package seedu.duke.model;

public class Cmd {
    private String description;
    private String msg = "";

    public Cmd(String description) {
        this.description = description;
    }

    //void constructor
    public Cmd() {
        this.description = "";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
