package duke;

public class Task {
    String description;
    char type;
    int status;  //0=incomplete, 1=complete
    String timeFrame;  //for deadlines or events

    public Task(String description, char type, int status, String time) {
        this.description = description;
        this.type = type;
        this.status = status;
        this.timeFrame = time;
    }

    public int get_Status(){
        return status;
    }

    public String get_Description(){
        return description;
    }

    public String get_TimeFrame(){
        return timeFrame;
    }

    public void changeStatus(int status) {
        this.status = status;
    }
}
